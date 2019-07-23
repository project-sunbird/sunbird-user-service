package org.sunbird.organization;

import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.organization.service.IOrgService;
import org.sunbird.organization.service.OrgServiceImpl;
import org.sunbird.request.Request;

@ActorConfig(
  tasks = {"readOrg"},
  asyncTasks = {}
)
public class OrgReadActor extends BaseActor {

  private static IOrgService INSTANCE = new OrgServiceImpl();

  @Override
  public void onReceive(Request request) {
    System.out.println("org read!!");
  }
}
