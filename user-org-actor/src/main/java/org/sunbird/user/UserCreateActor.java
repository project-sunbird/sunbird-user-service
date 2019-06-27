package org.sunbird.user;

import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actor.core.BaseActor;
import org.sunbird.dao.DaoImplType;
import org.sunbird.dao.IUserDao;
import org.sunbird.dao.UserDaoFactory;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

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
