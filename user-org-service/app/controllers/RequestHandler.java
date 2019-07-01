package controllers;

import akka.pattern.Patterns;
import akka.util.Timeout;
import org.everit.json.schema.ValidationException;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.ResponseCode;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.UserOrgJsonKey;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;
import play.mvc.Results;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class RequestHandler extends BaseController {

    public CompletionStage<Result> handleRequest(Request request, HttpExecutionContext httpExecutionContext, String operation) throws Exception {
        Object obj;
        CompletableFuture<String> cf = new CompletableFuture<>();
        request.setOperation(operation);
        ProjectLogger.log("UserController:createUser :: Requested operation " + request.getOperation());
        startTrace("handleRequest");
        Timeout t = new Timeout(Long.valueOf(request.getTimeout()), TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(getActorRef(operation), request, t);
        obj = Await.result(future, t.duration());
        endTrace("handleRequest");
        return handelResponse(obj,httpExecutionContext);
    }

    /**
     * This method will handel all the failure response of Api calls.
     *
     * @param exception
     * @return
     */
    public static CompletionStage<Result> handelFailureResponse(Object exception, HttpExecutionContext httpExecutionContext) {

        Response response = new Response();
        CompletableFuture<String> future = new CompletableFuture<>();
        if (exception instanceof org.everit.json.schema.ValidationException) {
            String exceptionsMessages = String.join(" , ", ((ValidationException) exception).getAllMessages());
            response.put(UserOrgJsonKey.MESSAGE, exceptionsMessages);
            response.setResponseCode(ResponseCode.BAD_REQUEST);
            future.complete(jsonifyResponseObject(response));
            return future.thenApplyAsync(Results::badRequest, httpExecutionContext.current());
        } else if (exception instanceof BaseException) {
            BaseException ex = (BaseException) exception;
            response.setResponseCode(ResponseCode.BAD_REQUEST);
            response.put(UserOrgJsonKey.MESSAGE, ex.getMessage());
            future.complete(jsonifyResponseObject(response));
            if (ex.getResponseCode() == Results.badRequest().status()) {
                return future.thenApplyAsync(Results::badRequest, httpExecutionContext.current());
            } else {
                return future.thenApplyAsync(Results::internalServerError, httpExecutionContext.current());
            }
        } else {
            response.setResponseCode(ResponseCode.SERVER_ERROR);
            response.put(UserOrgJsonKey.MESSAGE,localizerObject.getMessage(IResponseMessage.INTERNAL_ERROR,null));
            future.complete(jsonifyResponseObject(response));
            return future.thenApplyAsync(Results::internalServerError, httpExecutionContext.current());
        }
    }

    /**
     * this method will divert the response on the basis of success and failure
     * @param object
     * @param httpExecutionContext
     * @return
     */
    public  static CompletionStage<Result> handelResponse(Object object, HttpExecutionContext httpExecutionContext) {

        if (object instanceof Response) {
            Response response = (Response) object;
            return handelSuccessResponse(response, httpExecutionContext);
        } else {
            return handelFailureResponse(object, httpExecutionContext);
        }
    }

    /**
     * This method will handel all the success response of Api calls.
     *
     * @param response
     * @return
     */

    public static CompletionStage<Result> handelSuccessResponse(Response response, HttpExecutionContext httpExecutionContext) {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete(jsonifyResponseObject(response));
        return future.thenApplyAsync(Results::ok, httpExecutionContext.current());
    }
}
