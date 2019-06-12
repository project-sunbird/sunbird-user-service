package controllers.tac;

import controllers.BaseController;
import java.util.concurrent.CompletionStage;
import play.mvc.Result;

/** This controller will handle all request related to user terms and conditions. */
public class UserTncController extends BaseController {

  /**
   * This action method is used to accept the agreement when user login for first time.
   *
   * @return success response
   */
  public CompletionStage<Result> acceptTnc() {
    startTrace("acceptTnc");
    CompletionStage<Result> response = handelRequest();
    endTrace("acceptTnc");
    return response;
  }
}
