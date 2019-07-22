package org.sunbird.user;

import java.util.Map;
import org.sunbird.BaseActor;
import org.sunbird.DaoImplType;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actorOperation.UserActorOperations;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.ESException;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.ResponseCode;
import org.sunbird.request.Request;
import org.sunbird.user.dao.IUserDao;
import org.sunbird.user.dao.UserDaoFactory;
import org.sunbird.util.ProjectLogger;

/**
 * this actor class is used to insert user data inside elsatic search
 *
 * @author Amit Kumar
 */
@ActorConfig(
  tasks = {"userESCreate"},
  dispatcher = "user-dispatcher",
  asyncTasks = {}
)
public class UserESCreateActor extends BaseActor {
  IUserDao userESDao = (IUserDao) UserDaoFactory.getDaoImpl(DaoImplType.ES.getType());

  @Override
  public void onReceive(Request request) throws Throwable {
    if (UserActorOperations.USER_ES_CREATE
        .getOperation()
        .equalsIgnoreCase(request.getOperation())) {
      create(request);
    } else {
      onReceiveUnsupportedMessage(this.getClass().getName());
    }
  }

  private void create(Request request) throws BaseException {
    try {
      Map<String, Object> user = request.getRequest();
      userESDao.createUser(user);
    } catch (Exception ex) {
      ProjectLogger.log(
          "UserESCreateActor:create : Exception occurred while creating user in Elastic search. ",
          ex);
      throw new ESException.SaveException(
          IResponseMessage.ES_SAVE_ERROR,
          localizer.getMessage(IResponseMessage.ES_SAVE_ERROR, null),
          ResponseCode.SERVER_ERROR.getCode());
    }
  }
}
