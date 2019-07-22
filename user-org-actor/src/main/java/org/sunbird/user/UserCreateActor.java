package org.sunbird.user;

import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actorOperation.UserActorOperations;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.service.IUserService;
import org.sunbird.user.service.UserServiceImpl;

/**
 * this actor class is used when the operation provided is createUser , to create a user
 *
 * @author Amit Kumar
 */
@ActorConfig(
  tasks = {"createUser"},
  dispatcher = "user-dispatcher",
  asyncTasks = {}
)
public class UserCreateActor extends BaseActor {

  private Response response = null;
  private IUserService userService = null;

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
   *
   * @param request
   */
  private void createUser(Request request) {
    startTrace("createUser");
    try {
      userService = new UserServiceImpl();
      response = userService.createUser(request);
      sender().tell(response, self());
    } catch (BaseException ex) {
      sender().tell(ex, self());
    }
    endTrace("createUser");
  }
}
