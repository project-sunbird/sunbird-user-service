package controllers.logsmanager;

import controllers.BaseController;
import java.util.concurrent.CompletionStage;
import org.json.JSONObject;
import play.mvc.Result;
import utils.validator.logvalidator.LogModelValidator;

/** This controller is responsible to manage the dynamic configuration of Logs */
public class LogController extends BaseController {

  /**
   * This action method is responsible to set the Log Level dynamically using Api.
   *
   * @return
   */
  public CompletionStage<Result> setLogLevel() {
    startTrace("setLogLevel");
    return handleLogRequest(
        request -> {
          JSONObject jsonObject = jsonifyRequestObject(request().body().asJson());
          new LogModelValidator().validate(jsonObject, request().uri());
          return null;
        });
  }
}
