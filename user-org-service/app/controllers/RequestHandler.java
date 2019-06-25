package controllers;

import akka.pattern.Patterns;
import akka.util.Timeout;
import org.apache.commons.lang3.StringUtils;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.message.Localizer;
import org.sunbird.exception.message.ResponseCode;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.util.ProjectLogger;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;
import play.mvc.Results;
import scala.concurrent.Await;
import scala.concurrent.Future;
import utils.RequestMapper;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class RequestHandler  extends BaseController{

    public CompletionStage<Result> createHandelRequest(play.mvc.Http.Request request, HttpExecutionContext httpExecutionContext) {
        Object obj = null;
        CompletableFuture<String> cf = new CompletableFuture<>();
        Request req = new Request();
        try {
        req = (Request) RequestMapper.mapRequest(request.body().asJson(), Request.class);
        req.setOperation("createUser");
        ProjectLogger.log("UserController:createUser :: Requested operation "+req.getOperation());
        startTrace("handelRequest");
        Timeout t = new Timeout(Long.valueOf(req.getTimeout()), TimeUnit.SECONDS);
        Future<Object> future = Patterns.ask(getActorRef("createUser"),req,req.getTimeout());
        obj = Await.result(future,t.duration());
            if(obj instanceof Response){
                Response res = (Response)obj;
                cf.complete(String.valueOf(Json.toJson(res)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        endTrace("handelRequest");
        return cf.thenApplyAsync(Results::ok, httpExecutionContext.current());
    }

    /**
     * Common exception response handler method.
     *
     * @param e Exception
     * @param request play.mvc.Http.Request
     * @return Result
     */
    public Result createExceptionResponse(Exception e, Request request) {
        Request req = request;
        BaseException exception = null;
        if (e instanceof BaseException) {
            exception = (BaseException) e;
        } else {
            exception = new BaseException(IResponseMessage.INTERNAL_ERROR, Localizer.getInstance().getMessage("ResponseCode.internalError",null),ResponseCode.SERVER_ERROR.getCode());
        }
        // cleaning request info ...
        return Results.status(
                exception.getResponseCode(),
                Json.toJson(createResponseOnException(req, exception)));
    }

    public static Response createResponseOnException(
            Request request, BaseException exception) {
        Response response = new Response();
        response.setVer("");
        /**
         * TODO revisit this code snippet
         */
        //response.setResponseCode(ResponseCode.getHeaderResponseCode(exception.getResponseCode()));
        //ResponseCode code = ResponseCode.getResponse(exception.getCode());
        //if (code == null) {
        //    code = ResponseCode.SERVER_ERROR;
        //}
        if (response.getParams() != null) {
            response.getParams().setStatus(response.getParams().getStatus());
            if (exception.getCode() != null) {
                response.getParams().setStatus(exception.getCode());
            }
            if (!StringUtils.isBlank(response.getParams().getErrmsg())
                    && response.getParams().getErrmsg().contains("{0}")) {
                response.getParams().setErrmsg(exception.getMessage());
            }
        }
        return response;
    }
}
