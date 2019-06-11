package controllers.tac;

import controllers.BaseController;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

/** This controller will handle all request related to user terms and conditions. */
public class UserTncController extends BaseController {

  /**
   * This action method is used to accept the agreement when user login for first time.
   *
   * @return success response
   */
  public CompletionStage<Result> acceptTnc() {
    ProjectLogger.log(
            "OrgTypeController :listOrgType: request reached to orgMember controller with request "+ request().body(), LoggerEnum.INFO.name());
    return handelRequest();
  }
}
