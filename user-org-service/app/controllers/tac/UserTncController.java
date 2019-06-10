package controllers.tac;

import controllers.BaseController;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
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
    return handelRequest();
  }
}
