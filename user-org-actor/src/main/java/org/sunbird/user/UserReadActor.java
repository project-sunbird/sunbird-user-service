package org.sunbird.user;

import org.sunbird.BaseActor;
import org.sunbird.DaoImplType;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actorOperation.UserActorOperations;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.dao.IUserESDao;
import org.sunbird.user.dao.UserDaoFactory;
import org.sunbird.util.jsonkey.JsonKey;


/**
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"readUserById"},
        dispatcher = "search-read-dispatcher",
        asyncTasks = {}

)
public class UserReadActor extends BaseActor {

    IUserESDao userESDao = (IUserESDao) UserDaoFactory.getInstance().getDaoImpl(DaoImplType.ES.getType());


    @Override
    public void onReceive(Request request) throws Throwable {
        if (UserActorOperations.READ_USER_BY_ID.getOperation().equalsIgnoreCase(request.getOperation())) {
            readUserById(request);
        } else {
            onReceiveUnsupportedMessage(this.getClass().getName());
        }
    }

    public void readUserById(Request request) throws BaseException {
        Response response = userESDao.getUserById((String) request.getRequest().get(JsonKey.USER_ID));
        sender().tell(response, self());
    }

}
