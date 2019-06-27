package org.sunbird.user;

import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.DaoImplType;
import org.sunbird.user.dao.IUserDao;
import org.sunbird.user.dao.UserDaoFactory;

/**
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"createUser"},
        dispatcher = "user-dispatcher",
        asyncTasks = {}
)
public class UserCreateActor  extends BaseActor {

    private Response response = null;

    @Override
    public void onReceive(Request request) throws Throwable {
        if(request.getOperation().equalsIgnoreCase("createUser")){
            createUser(request);
        } else {
            onReceiveUnsupportedMessage("UserCreateActor");
        }
    }

    private void createUser(Request request) {
        IUserDao userDao = UserDaoFactory.getInstance().getDaoImpl(DaoImplType.OPEN_SABER.getType());
        response = userDao.addUser(request.getRequest());
        sender().tell(response,self());
    }

}
