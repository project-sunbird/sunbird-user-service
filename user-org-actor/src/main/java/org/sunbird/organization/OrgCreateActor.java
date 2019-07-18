package org.sunbird.organization;

import org.sunbird.BaseActor;
import org.sunbird.OperationValidator;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actorOperation.OrgActorOperations;
import org.sunbird.request.Request;

/** @author Amit Kumar */
@ActorConfig(
  tasks = {"createOrg"},
  asyncTasks = {}
)
public class OrgCreateActor extends BaseActor {

  @Override
  public void onReceive(Request request) {
    if (OperationValidator.matchOperationName(
        OrgActorOperations.CREATE_ORG.getOperation(), request.getOperation())) {
      System.out.println("Org created!!!");
    }
  }
}
