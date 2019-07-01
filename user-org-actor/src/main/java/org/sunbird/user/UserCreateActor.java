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

/**
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
    public void onReceive(Request  request) throws Throwable {
        if(request.getOperation().equalsIgnoreCase(UserActorOperations.CREATE_USER.getOperation())){
            createUser(request);
        } else {
            onReceiveUnsupportedMessage(this.getClass().getName());
        }
    }

    private void createUser(Request request) {
        IUserOSDao userDao = (IUserOSDao) UserDaoFactory.getInstance().getDaoImpl(DaoImplType.OPEN_SABER.getType());
        try {
            response = userDao.addUser(request.getRequest());
            sender().tell(response, self());
        } catch (BaseException ex) {
            sender().tell(ex, self());
        }
    }

}
