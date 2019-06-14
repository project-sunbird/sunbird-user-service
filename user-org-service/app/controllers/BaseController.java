package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.logsmanager.validator.LogValidator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.UserOrgJsonKey;
import org.sunbird.util.request.Request;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import utils.mapper.RequestMapper;

/**
 * This controller we can use for writing some common method to handel api request.
 * CompletableFuture: A Future that may be explicitly completed (setting its value and status), and
 * may be used as a CompletionStage, supporting dependent functions and actions that trigger upon
 * its completion. CompletionStage: A stage of a possibly asynchronous computation, that performs an
 * action or computes a value when another CompletionStage completes
 *
 * @author Anmol
 */
public class BaseController extends Controller {

  @Inject private HttpExecutionContext httpObject;

  public String getDummyResponse() {
    startTrace("getDummyResponse");
    String dummyResponse =
        "{\"id\":\"api.user.200ok\",\"ver\":\"v1\",\"ts\":\"2019-01-17 16:53:26:286+0530\",\"params\":{\"resmsgid\":null,\"msgid\":\"8e27cbf5-e299-43b0-bca7-8347f7ejk5abcf\",\"err\":null,\"status\":\"success\",\"errmsg\":null},\"responseCode\":\"OK\",\"result\":{\"response\":{\"response\":\"SUCCESS\",\"errors\":[]}}}";
    endTrace("getDummyResponse");
    return dummyResponse;
  }

  public CompletionStage<Result> handelRequest() {
    Http.RequestBody requestBody = request().body();
    startTrace("handelRequest");
    CompletableFuture<String> cf = new CompletableFuture<>();
    cf.complete(getDummyResponse());
    endTrace("handelRequest");
    return cf.thenApplyAsync(Results::ok, httpObject.current());
  }

  public long getTimeStamp() {
    return System.currentTimeMillis();
  }

  public void startTrace(String tag) {
    ProjectLogger.log(
        String.format("%s:%s:started at %s", this.getClass().getSimpleName(), tag, getTimeStamp()),
        LoggerEnum.DEBUG.name());
  }

  public void endTrace(String tag) {
    ProjectLogger.log(
        String.format("%s:%s:ended at %s", this.getClass().getSimpleName(), tag, getTimeStamp()),
        LoggerEnum.DEBUG.name());
  }

  /**
   * This method is used specifically to handel Log Apis frequest this will set log levels and then
   * return the CompletionStage of Result
   *
   * @return
   */
  public CompletionStage<Result> handleLogRequest() throws JsonProcessingException {
    startTrace("handleLogEvent");
    Map<String, Object> responseMap = new HashMap<>();
    ObjectMapper mapper = new ObjectMapper();
    CompletableFuture<String> cf = new CompletableFuture<>();
    try {
      Http.RequestBody requestBody = request().body();
      Request request = (Request) RequestMapper.mapRequest(requestBody.asJson(), Request.class);
      if (LogValidator.checkLogValidationError(request)) {
        responseMap =
            prepareRequestSuccessResponse(
                "logLevel successfully set to " + request.get(UserOrgJsonKey.LOG_LEVEL));
        cf.complete(mapper.writeValueAsString(responseMap));
        endTrace("handleLogEvent");
        ProjectLogger.setUserOrgServiceProjectLogger(
            (String) request.get(UserOrgJsonKey.LOG_LEVEL));
      }
      return cf.thenApplyAsync(Results::ok, httpObject.current());
    } catch (Exception e) {
      responseMap = prepareRequestFailureResponse(e.getLocalizedMessage());
      cf.complete(mapper.writeValueAsString(responseMap));
      ProjectLogger.log(
          String.format(
              "%s:%s:exception occurred %s",
              this.getClass().getSimpleName(), "handleLogRequest", e.getLocalizedMessage()),
          LoggerEnum.ERROR.name());
      return cf.thenApplyAsync(Results::badRequest, httpObject.current());
    }
  }

  public Map<String, Object> prepareRequestSuccessResponse(String message) {
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put(UserOrgJsonKey.ERROR, false);
    responseMap.put(UserOrgJsonKey.MESSAGE, message);
    return responseMap;
  }

  public Map<String, Object> prepareRequestFailureResponse(String message) {
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put(UserOrgJsonKey.ERROR, true);
    responseMap.put(UserOrgJsonKey.MESSAGE, message);
    return responseMap;
  }
}
