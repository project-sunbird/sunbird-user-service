package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.logsmanager.validator.LogValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;

import akka.actor.ActorRef;
import org.sunbird.Application;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.ResponseMessage;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.UserOrgJsonKey;
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

  /** We injected HttpExecutionContext to decrease the response time of APIs. */
  @Inject private HttpExecutionContext httpExecutionContext;

  /**
   * This is temporary method we use get dummyresponse to check APIs.
   *
   * @return string
   */
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
    CompletableFuture<String> future = new CompletableFuture<>();
    future.complete(getDummyResponse());
    endTrace("handelRequest");
    return future.thenApplyAsync(Results::ok, httpExecutionContext.current());
  }

  /**
   * This method will return the current timestamp.
   *
   * @return long
   */
  public long getTimeStamp() {
    return System.currentTimeMillis();
  }

  /**
   * This method we used to print the logs of starting time of methods
   *
   * @param tag
   */
  public void startTrace(String tag) {
    ProjectLogger.log(
        String.format("%s:%s:started at %s", this.getClass().getSimpleName(), tag, getTimeStamp()),
        LoggerEnum.DEBUG.name());
  }

  /**
   * This method we used to print the logs of ending time of methods
   *
   * @param tag
   */
  public void endTrace(String tag) {
    ProjectLogger.log(
        String.format("%s:%s:ended at %s", this.getClass().getSimpleName(), tag, getTimeStamp()),
        LoggerEnum.DEBUG.name());
  }

  protected ActorRef getActorRef(String operation) throws BaseException {
    return Application.getInstance().getActorRef(operation);
  }

  public CompletionStage<Result> createHandelRequest(play.mvc.Http.Request request) {
    return new RequestHandler().createHandelRequest(request,httpExecutionContext);
  }

  /**
   * This method will redirect Response object on the basis of error is present or not present in
   * response
   *
   * @param response
   * @return CompletionStage<Result>
   */
  public CompletionStage<Result> handelResponse(Response response) {
    String jsonifyResponse = jsonifyResponseObject(response);
    return (Boolean) response.get(UserOrgJsonKey.ERROR)
        ? handelFailureResponse(jsonifyResponse)
        : handelSuccessResponse(jsonifyResponse);
  }

  /**
   * This method will handel all the success response of Api calls.
   *
   * @param response
   * @return
   */
  public CompletionStage<Result> handelSuccessResponse(String response) {
    CompletableFuture<String> future = new CompletableFuture<>();
    if (!response.isEmpty()) {
      future.complete(response);
      return future.thenApplyAsync(Results::ok, httpExecutionContext.current());
    } else {
      future.complete(ResponseMessage.INTERNAL_ERROR);
      return future.thenApplyAsync(Results::internalServerError, httpExecutionContext.current());
    }
  }

  /**
   * This method will handel all the failure response of Api calls.
   *
   * @param response
   * @return
   */
  public CompletionStage<Result> handelFailureResponse(String response) {
    CompletableFuture<String> future = new CompletableFuture<>();
    if (!response.isEmpty()) {
      future.complete(response);
      return future.thenApplyAsync(Results::badRequest, httpExecutionContext.current());
    } else {
      future.complete(ResponseMessage.INTERNAL_ERROR);
      return future.thenApplyAsync(Results::internalServerError, httpExecutionContext.current());
    }
  }

  /**
   * This method is responsible to convert Response object into json
   *
   * @param response
   * @return string
   */
  public String jsonifyResponseObject(Response response) {

    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(response);
    } catch (Exception e) {
      return UserOrgJsonKey.EMPTY_STRING;
    }
  }

  /**
   * This method is used specifically to handel Log Apis request this will set log levels and then
   * return the CompletionStage of Result
   *
   * @return
   */
  public CompletionStage<Result> handleLogRequest() {
    startTrace("handleLogRequest");
    Response response = new Response();
    Http.RequestBody requestBody = request().body();
    Request request = null;
    try{
      request = (Request) RequestMapper.mapRequest(requestBody.asJson(), Request.class);
    } catch (Exception ex){
      ex.printStackTrace();
    }

    if (LogValidator.isLogParamsPresent(request)) {
      if (LogValidator.isValidLogLevelPresent((String) request.get(UserOrgJsonKey.LOG_LEVEL))) {
        ProjectLogger.setUserOrgServiceProjectLogger(
            (String) request.get(UserOrgJsonKey.LOG_LEVEL));
        response.put(UserOrgJsonKey.ERROR, false);
        response.put(
            UserOrgJsonKey.MESSAGE,
            "Log Level successfully set to " + request.get(UserOrgJsonKey.LOG_LEVEL));
      } else {
        List<Enum> supportedLogLevelsValues = new ArrayList<>(EnumSet.allOf(LoggerEnum.class));
        response.put(UserOrgJsonKey.ERROR, true);
        response.put(
            UserOrgJsonKey.MESSAGE,
            "Valid Log Levels are " + Arrays.asList(supportedLogLevelsValues.toArray()));
      }
    } else {
      response.put(UserOrgJsonKey.ERROR, true);
      response.put(
          UserOrgJsonKey.MESSAGE, "Missing Mandatory Request Param " + UserOrgJsonKey.LOG_LEVEL);
    }
    return handelResponse(response);
  }
}
