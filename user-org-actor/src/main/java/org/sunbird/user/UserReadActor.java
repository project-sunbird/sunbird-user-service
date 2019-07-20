package org.sunbird.user;

import org.sunbird.BaseActor;
import org.sunbird.DaoImplType;
import org.sunbird.ServiceImplType;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actorOperation.UserActorOperations;
import org.sunbird.exception.BaseException;
import org.sunbird.factory.ServiceFactory;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.dao.IUserESDao;
import org.sunbird.user.dao.UserDaoFactory;
import org.sunbird.user.service.IUserService;
import org.sunbird.user.service.UserServiceImpl;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.jsonkey.JsonKey;


/**
 * this actor class is used to read a used when operation provided is readUserById.
 *
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"readUserById"},
        dispatcher = "search-read-dispatcher",
        asyncTasks = {}

)
public class UserReadActor extends BaseActor {

    private IUserService userService = null;
    IUserESDao userESDao = (IUserESDao) UserDaoFactory.getDaoImpl(DaoImplType.ES.getType());


    @Override
    public void onReceive(Request request) throws Throwable {
        if (UserActorOperations.READ_USER_BY_ID.getOperation().equalsIgnoreCase(request.getOperation())) {
            readUserById(request);
        } else {
            onReceiveUnsupportedMessage(this.getClass().getName());
        }
    }

    /**
     * this method is used to read user from elastic search.
     *
     * @param request
     * @throws BaseException
     */
    public void readUserById(Request request) throws BaseException {
        startTrace("readUserById");
        Response response = null;
        try {
            response = userESDao.getUserById((String) request.getRequest().get(JsonKey.USER_ID));
        } catch (Exception e) {
            ProjectLogger.log(
                    "UserReadActor:readUserById: "
                            + "Exception in getting the record from ES : "
                            + e.getMessage(),
                    LoggerEnum.ERROR.name());
            ProjectLogger.log("Exception occurred while reading user ES.", e);
            userService = (IUserService) ServiceFactory.getService(ServiceImplType.USER.getServiceType());
            //userService = new UserServiceImpl();
            response = userService.readUser(request);
        }
        endTrace("readUserById");
        sender().tell(response, self());
    }

}
