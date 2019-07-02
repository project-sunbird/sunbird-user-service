package org.sunbird.user;

import org.sunbird.BaseActor;
import org.sunbird.DaoImplType;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actorOperation.UserActorOperations;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.dao.IUserOSDao;
import org.sunbird.user.dao.UserDaoFactory;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;

/**
 * this actor class is used when the operation provided is createUser , to create a user
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"createUser"},
        dispatcher = "user-dispatcher",
        asyncTasks = {}
)

public class UserCreateActor extends BaseActor {

    private Response response = null;

    @Override
    public void onReceive(Request request) {
        if (UserActorOperations.CREATE_USER.getOperation().equalsIgnoreCase(request.getOperation())) {
            createUser(request);
        } else {
            onReceiveUnsupportedMessage(this.getClass().getName());
        }
    }

    /**
     * this method is used to create the user
     * @param request
     */
    private void createUser(Request request) {
        ProjectLogger.log(String.format("%s:%s:method started at %s",this.getClass().getSimpleName(),"createUser",System.currentTimeMillis()), LoggerEnum.DEBUG.name());
        IUserOSDao userDao = (IUserOSDao) UserDaoFactory.getDaoImpl(DaoImplType.OS.getType());
        try {
            response = userDao.createUser(request.getRequest());
            ProjectLogger.log(String.format("%s:%s:method ended at %s",this.getClass().getSimpleName(),"createUser",System.currentTimeMillis()), LoggerEnum.DEBUG.name());
            sender().tell(response, self());
        } catch (BaseException ex) {
            sender().tell(ex, self());
        }
    }

}
