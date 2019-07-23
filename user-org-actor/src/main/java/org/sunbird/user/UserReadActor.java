package org.sunbird.user;

import org.sunbird.BaseActor;
import org.sunbird.DaoImplType;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.dao.IUserDao;
import org.sunbird.user.dao.UserDaoFactory;
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

  IUserDao userESDao = (IUserDao) UserDaoFactory.getDaoImpl(DaoImplType.ES.getType());

  @Override
  public void onReceive(Request request) throws Throwable {
    readUserById(request);
  }

  /**
   * this method is used to read user from elastic search.
   *
   * @param request
   * @throws BaseException
   */
  public void readUserById(Request request) throws BaseException {
    startTrace("readUserById");
    Response response = userESDao.getUserById((String) request.getRequest().get(JsonKey.USER_ID));
    endTrace("readUserById");
    sender().tell(response, self());
  }
}
