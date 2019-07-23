package org.sunbird.organization;

import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.exception.BaseException;
import org.sunbird.organization.service.IOrgService;
import org.sunbird.organization.service.OrgServiceImpl;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

@ActorConfig(
  tasks = {"searchOrg"},
  asyncTasks = {}
)
public class OrgSearchActor extends BaseActor {

  private static IOrgService INSTANCE = new OrgServiceImpl();

  @Override
  public void onReceive(Request request) {
    search(request);
  }

  /**
   * this method is used to search org from elastic search
   *
   * @param request
   * @throws BaseException
   */
  public void search(Request request) {
    startTrace("searchOrg");
    Response response = INSTANCE.search(request);
    endTrace("searchOrg");
    sender().tell(response, self());
  }
}
