package org.sunbird.user.service;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.util.Timeout;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.sunbird.Application;
import org.sunbird.DaoImplType;
import org.sunbird.actorOperation.UserActorOperations;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.dao.IUserDao;
import org.sunbird.user.dao.UserDaoFactory;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.jsonkey.JsonKey;
import scala.concurrent.Await;
import scala.concurrent.Future;

public class UserServiceImpl implements IUserService {

    private static volatile IUserService iUserService;

    public static IUserService getInstance() {
        if(iUserService == null) {
            synchronized(UserServiceImpl.class){
                iUserService = new UserServiceImpl();
            }
        }
        return iUserService;
    }

    private List<String> userSubEntityList = new ArrayList<>(Arrays.asList(JsonKey.ADDRESS,JsonKey.JOB_PROFILE,JsonKey.EDUCATION,JsonKey.EXTERNAL_IDS));

    @Override
    public Response createUser(Request request) throws BaseException{
        Response response = new Response();
        IUserDao userDao = (IUserDao) UserDaoFactory.getDaoImpl(DaoImplType.OS.getType());
        Response userResponse = userDao.createUser(getUser(request));
        response.getResult().put(JsonKey.USER_ID,userResponse.get(JsonKey.ID));
        Response userAttributeResponse = saveUserAttributes(request,(String)userResponse.get(JsonKey.ID));
        saveESUserData(userAttributeResponse,userResponse);
        return response;
    }

    private Map<String,Object> getUser(Request request) {
        Map<String,Object> userMap = new HashMap<>();
        userMap.putAll(request.getRequest());
        userSubEntityList.stream().forEach(key -> {
            userMap.remove(key);
        });
        Map<String,Object> userReq = new HashMap<>();
        userReq.put(StringUtils.capitalize(JsonKey.USER),userMap);
        return userReq;
    }

    private Response saveUserAttributes(Request request, String userId) {
        try {
            Request usrRequest = new Request();
            usrRequest.getRequest().putAll(request.getRequest());
            usrRequest.getRequest().put(JsonKey.USER_ID,userId);
            ActorRef actorRef = Application.getInstance().getActorRef(UserActorOperations.SAVE_USER_ATTRIBUTES.getOperation());
            Timeout t = new Timeout(Long.valueOf(request.getTimeout()), TimeUnit.SECONDS);
            Future<Object> future = Patterns.ask(actorRef, usrRequest, t);
            Object obj = Await.result(future, t.duration());
            return (Response)obj;
        }catch (Exception ex){
            ProjectLogger.log("UserServiceImpl:saveUserAttributes : Exception occurred while saving user sub entity like address,education and job profile details request.",ex);
        }
        return null;
    }

    private void saveESUserData(Response userAttributeResponse, Response userResponse) {
        try {
            Request esRequest = new Request();
            userAttributeResponse.getResult().remove(JsonKey.ERRORS);
            esRequest.getRequest().putAll(userAttributeResponse.getResult());
            Map<String,Object> userMap = (Map<String,Object>)userResponse.getResult().get(StringUtils.capitalize(JsonKey.USER));
            userMap.put(JsonKey.USER_ID,userResponse.get(JsonKey.ID));
            userMap.put(JsonKey.ID,userResponse.get(JsonKey.ID));
            esRequest.getRequest().putAll(userMap);
            ActorRef actorRef = Application.getInstance().getActorRef(UserActorOperations.SAVE_ES_USER.getOperation());
            esRequest.setOperation(UserActorOperations.USER_ES_CREATE.getOperation());
            actorRef.tell(esRequest,ActorRef.noSender());
        }catch (Exception ex){
            ProjectLogger.log("UserServiceImpl:saveESUserData : Exception occurred while creating user data request for ES.",ex);
        }
    }

    @Override
    public Response readUser(String userId) throws BaseException {
        IUserDao userDao = (IUserDao) UserDaoFactory.getDaoImpl(DaoImplType.OS.getType());
        return userDao.getUserById(userId);
    }
}
