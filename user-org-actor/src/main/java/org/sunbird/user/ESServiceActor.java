package org.sunbird.user;

import akka.actor.ActorRef;
import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actorOperation.UserActorOperations;
import org.sunbird.request.Request;

/**
 * this actor class is used to process the user CRUD operation for Elasticsearch and can act as
 * parent actor to supervise the child actor.
 *
 * @author Amit Kumar
 */
@ActorConfig(
  tasks = {"saveESUser"},
  dispatcher = "user-dispatcher",
  asyncTasks = {}
)
public class ESServiceActor extends BaseActor {

  @Override
  public void onReceive(Request request) throws Throwable {

    String operation = request.getOperation();
    switch (operation) {
      case "userESCreate":
        createUser(request);
        break;

      case "userESUpdate":
        updateUpdate(request);
        break;

      default:
        onReceiveUnsupportedMessage(this.getClass().getName());
    }
  }

  private void createUser(Request request) {
    ActorRef actorRef = getActorRef(UserActorOperations.USER_ES_CREATE.getOperation());
    request.setOperation(UserActorOperations.USER_ES_CREATE.getOperation());
    actorRef.tell(request, self());
  }

  private void updateUpdate(Request request) {}
}
