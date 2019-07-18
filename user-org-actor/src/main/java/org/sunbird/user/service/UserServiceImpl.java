package org.sunbird.user.service;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.sunbird.Application;
import org.sunbird.DaoImplType;
import org.sunbird.actorOperation.UserActorOperations;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.dao.IUserOSDao;
import org.sunbird.user.dao.UserDaoFactory;
import org.sunbird.util.jsonkey.JsonKey;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UserServiceImpl implements IUserService {


    @Override
    public Response createUser(Request request) throws BaseException{
        Response response = new Response();
        Map<String,Object> userReq = new HashMap<>();
        userReq.put(StringUtils.capitalize(JsonKey.USER),request.getRequest());
        IUserOSDao userDao = (IUserOSDao) UserDaoFactory.getDaoImpl(DaoImplType.OS.getType());
        Response userDaoResponse = userDao.createUser(userReq);
        Response userResponse = saveUserAttributes(request);
        response.getResult().put(JsonKey.USER_ID,userDaoResponse.get(JsonKey.USER_ID));
        return response;
    }

    private Response saveUserAttributes(Request request) {
        Response response = null;
        try {
            ActorRef actorRef = Application.getInstance().getActorRef(UserActorOperations.SAVE_USER_ATTRIBUTES.getOperation());
            Timeout t = new Timeout(Long.valueOf(request.getTimeout()), TimeUnit.SECONDS);
            Future<Object> future = Patterns.ask(actorRef, request, t);
            Object obj = Await.result(future, t.duration());
            return null;
        }catch (Exception ex){

        }
        return response;
    }

    @Override
    public Response readUser(Request request) throws BaseException {
        ObjectNode idNode = JsonNodeFactory.instance.objectNode();
        ObjectNode userNode =  JsonNodeFactory.instance.objectNode();
        String recordId = (String) request.getRequest().get(JsonKey.USER_ID);
        idNode.put(JsonKey.OSID, recordId);
        userNode.set("User", idNode);
        IUserOSDao userDao = (IUserOSDao) UserDaoFactory.getDaoImpl(DaoImplType.OS.getType());
        return userDao.readUser(userNode);
    }
}
