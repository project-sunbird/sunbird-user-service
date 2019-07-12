package org.sunbird.user.service;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.sunbird.Application;
import org.sunbird.DaoImplType;
import org.sunbird.actorOperation.UserActorOperations;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.dao.IUserOSDao;
import org.sunbird.user.dao.UserDaoFactory;
import org.sunbird.user.pojo.User;
import org.sunbird.util.jsonkey.JsonKey;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UserServiceImpl implements IUserService {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Response createUser(Request request) throws BaseException{
        Response response = new Response();
        IUserOSDao userDao = (IUserOSDao) UserDaoFactory.getDaoImpl(DaoImplType.USER_OS.getType());
        Response userDaoResponse = userDao.createUser(getUser(request));
        response.getResult().put(JsonKey.USER_ID,userDaoResponse.get(JsonKey.ID));
        Response userResponse = saveUserAttributes(request,(String)userDaoResponse.get(JsonKey.ID));
        return response;
    }

    private Map<String,Object> getUser(Request request) {
        Map<String,Object> userReq = new HashMap<>();
        User user = mapper.convertValue(request.getRequest(),User.class);
        userReq.put(StringUtils.capitalize(JsonKey.USER),mapper.convertValue(user,Map.class));
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

        }
        return null;
    }
}
