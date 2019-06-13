package controllers.logsmanager;

import controllers.BaseController;
import java.util.concurrent.CompletionStage;
import play.mvc.Result;

/** This controller is responsible to manage the dynamic configuration of Logs */
public class LogController extends BaseController {

  public CompletionStage<Result> setLogLevel() {
    startTrace("setLogLevel");
    return handleLogRequest();
  }
}
