package controllers.logsmanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.BaseController;
import java.util.concurrent.CompletionStage;
import play.mvc.Result;

/** This controller is responsible to manage the dynamic configuration of Logs */
public class LogController extends BaseController {

  /**
   * This action method is responsible to set the Log Level dynamically using Api.
   *
   * @return
   */
  public CompletionStage<Result> setLogLevel() throws JsonProcessingException {
    return handleLogRequest();
  }
}
