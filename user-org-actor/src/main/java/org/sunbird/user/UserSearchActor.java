package org.sunbird.user;

import org.sunbird.BaseActor;
import org.sunbird.DaoImplType;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actorOperation.UserActorOperations;
import org.sunbird.dto.SearchDTO;
import org.sunbird.dto.SearchDtoMapper;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.dao.IUserESDao;
import org.sunbird.user.dao.UserDaoFactory;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;

/**
 * this actor class is used to search user from elastic search when operation provided searchUser.
 *
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"searchUser"},
        dispatcher = "search-read-dispatcher",
        asyncTasks = {}

)
public class UserSearchActor extends BaseActor {

    IUserESDao userESDao = (IUserESDao) UserDaoFactory.getDaoImpl(DaoImplType.ES.getType());


    @Override
    public void onReceive(Request request) throws Throwable {

        if (UserActorOperations.SEARCH_USER.getOperation().equalsIgnoreCase(request.getOperation())) {
            searchUser(request);
        } else {
            onReceiveUnsupportedMessage(this.getClass().getName());
        }
    }

    /**
     * this method is used to search user from elastic search
     *
     * @param request
     * @throws BaseException
     */
    public void searchUser(Request request) throws BaseException {
        ProjectLogger.log(String.format("%s:%s:method started at %s", this.getClass().getSimpleName(), "searchUser", System.currentTimeMillis()), LoggerEnum.DEBUG.name());
        SearchDtoMapper searchDTOMapper = SearchDtoMapper.getInstance();
        SearchDTO searchDto = searchDTOMapper.createSearchDto(request.getRequest());
        Response response = userESDao.searchUser(searchDto);
        ProjectLogger.log(String.format("%s:%s:method ended at %s", this.getClass().getSimpleName(), "searchUser", System.currentTimeMillis()), LoggerEnum.DEBUG.name());
        sender().tell(response, self());
    }

}
