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

/**
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"searchUser"},
        dispatcher = "search-read-dispatcher",
        asyncTasks = {}

)
public class UserSearchActor extends BaseActor {

    IUserESDao userESDao = (IUserESDao) UserDaoFactory.getInstance().getDaoImpl(DaoImplType.ES.getType());


    @Override
    public void onReceive(Request request) throws Throwable {

        if (UserActorOperations.SEARCH_USER.getOperation().equalsIgnoreCase(request.getOperation())) {
            searchUser(request);
        } else {
            onReceiveUnsupportedMessage(this.getClass().getName());
        }
    }

    public void searchUser(Request request) throws BaseException {
        Response response = userESDao.searchUser(request.getRequest());
        sender().tell(response, self());
    }

}
