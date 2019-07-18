package controllers;

import akka.actor.ActorRef;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import org.json.JSONObject;
import org.sunbird.Application;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.message.Localizer;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.jsonkey.JsonKey;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import utils.RequestMapper;
import utils.validator.IModelValidator;

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

  protected static Localizer localizerObject = Localizer.getInstance();

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

  /**
   * this method will take play.mv.http request and a validation function and lastly operation(Actor
   * operation) this method is validating the request and , it will map the request to our sunbird
   * Request class and make a call to requestHandler which is internally calling ask to actor this
   * method is used to handle all the request type which has requestBody
   *
   * @param req
   * @param validatorFunction
   * @param operation
   * @return
   */
  public CompletionStage<Result> handleRequest(
      play.mvc.Http.Request req, IModelValidator validatorFunction, String operation) {
    try {
      Request request = (Request) RequestMapper.mapRequest(req, Request.class);
      if (validatorFunction != null) {
        validatorFunction.validate();
      }
      return new RequestHandler().handleRequest(request, httpExecutionContext, operation);
    } catch (org.everit.json.schema.ValidationException ex) {
      return RequestHandler.handleFailureResponse(ex, httpExecutionContext);
    } catch (BaseException ex) {
      return RequestHandler.handleFailureResponse(ex, httpExecutionContext);
    } catch (Exception ex) {
      return RequestHandler.handleFailureResponse(ex, httpExecutionContext);
    }
  }

  /**
   * this method is used to handle the only GET requests.
   *
   * @param req
   * @param operation
   * @return
   */
  public CompletionStage<Result> handleRequest(Request req, String operation) {
    try {
      return new RequestHandler().handleRequest(req, httpExecutionContext, operation);
    } catch (org.everit.json.schema.ValidationException ex) {
      return RequestHandler.handleFailureResponse(ex, httpExecutionContext);
    } catch (BaseException ex) {
      return RequestHandler.handleFailureResponse(ex, httpExecutionContext);
    } catch (Exception ex) {
      return RequestHandler.handleFailureResponse(ex, httpExecutionContext);
    }
  }

  /**
   * this method is used to jsonify the request object
   *
   * @param request
   * @return JSONObject
   */
  public JSONObject jsonifyRequestObject(JsonNode request) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      String value = mapper.writeValueAsString(request);
      JSONObject requestAsJson = new JSONObject(value);
      return requestAsJson;
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * This method is responsible to convert Response object into json
   *
   * @param response
   * @return string
   */
  public static String jsonifyResponseObject(Response response) {

    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(response);
    } catch (Exception e) {
      return JsonKey.EMPTY_STRING;
    }
  }

  /**
   * This method is used specifically to handel Log Apis request this will set log levels and then
   * return the CompletionStage of Result
   *
   * @return
   */
  public CompletionStage<Result> handleLogRequest(IModelValidator validatorFunction) {
    Response response = new Response();
    Request request;
    try {
      validatorFunction.validate();
      request = (Request) RequestMapper.mapRequest(request(), Request.class);
      ProjectLogger.setUserOrgServiceProjectLogger((String) request.get(JsonKey.LOG_LEVEL));
      response.put(
          JsonKey.MESSAGE,
          "Log Level successfully set to "
              + ((String) request.get(JsonKey.LOG_LEVEL)).toUpperCase());
      return RequestHandler.handleSuccessResponse(response, httpExecutionContext);
    } catch (org.everit.json.schema.ValidationException ex) {
      ProjectLogger.log(
          String.format(
              "%s:%s:exception occurred in mapping Log level request error is %s",
              this.getClass().getSimpleName(), "handleLogRequest", ex.getAllMessages().toString()),
          LoggerEnum.ERROR.name());
      return RequestHandler.handleFailureResponse(ex, httpExecutionContext);
    } catch (Exception e) {
      return RequestHandler.handleFailureResponse(e, httpExecutionContext);
    }
  }
}
