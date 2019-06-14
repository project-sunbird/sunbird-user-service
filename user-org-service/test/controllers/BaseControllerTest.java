package controllers;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sunbird.util.UserOrgJsonKey;
import org.sunbird.util.response.Response;
import org.sunbird.util.responsecode.ResponseCode;
import play.Application;
import play.mvc.Result;
import play.test.Helpers;

public class BaseControllerTest {
  BaseController controllerObject;
  TestHelper testHelper;
  public static Application app;
  public static Map<String, String[]> headerMap;

  @Before
  public void setUp() throws Exception {
    controllerObject = new BaseController();
    testHelper = new TestHelper();
    app = Helpers.fakeApplication();
    Helpers.start(app);
    headerMap = testHelper.getHeaderMap();
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testSuccessResponse() {
    String dummyResponse =
        "{\"id\":\"api.user.200ok\",\"ver\":\"v1\",\"ts\":\"2019-01-17 16:53:26:286+0530\",\"params\":{\"resmsgid\":null,\"msgid\":\"8e27cbf5-e299-43b0-bca7-8347f7ejk5abcf\",\"err\":null,\"status\":\"success\",\"errmsg\":null},\"responseCode\":\"OK\",\"result\":{\"response\":{\"response\":\"SUCCESS\",\"errors\":[]}}}";
    assertEquals(dummyResponse, controllerObject.getDummyResponse());
  }

  @Test
  public void testFailureResponse() {
    String dummyResponse =
        "{\"id\":\"api.user.400ok\",\"ver\":\"v1\",\"ts\":\"2019-01-17 16:53:26:286+0530\",\"params\":{\"resmsgid\":null,\"msgid\":\"8e27cbf5-e299-43b0-bca7-8347f7ejk5abcf\",\"err\":null,\"status\":\"success\",\"errmsg\":null},\"responseCode\":\"OK\",\"result\":{\"response\":{\"response\":\"SUCCESS\",\"errors\":[]}}}";
    assertNotEquals(dummyResponse, controllerObject.getDummyResponse());
  }

  @Test
  public void testJsonifyResponseSuccess() {
    Response response = new Response();
    BaseController controller = new BaseController();
    response.put(UserOrgJsonKey.MESSAGE, ResponseCode.internalError.getErrorMessage());
    String jsonifyResponse = controller.jsonifyResponseObject(response);
    assertEquals(
        "{\"result\":{\"message\":\"Process failed,please try again later.\"}}", jsonifyResponse);
  }

  @Test
  public void testJsonifyResponseFailure() {
    Response response = new Response();
    BaseController controller = new BaseController();
    response.put(UserOrgJsonKey.MESSAGE, response.getResult());
    String jsonifyResponse = controller.jsonifyResponseObject(response);
    assertEquals(StringUtils.EMPTY, jsonifyResponse);
  }

  @Test
  public void testHandelSuccessResponseSuccess() throws ExecutionException, InterruptedException {

    Response response = new Response();
    response.put(UserOrgJsonKey.ERROR, true);
    response.put(UserOrgJsonKey.MESSAGE, ResponseCode.internalError.getErrorMessage());
    BaseController controller = new BaseController();
    CompletionStage<Result> future = controller.handelSuccessResponse(response);
    CompletableFuture<Result> cfuture = (CompletableFuture<Result>) future;
    assertEquals(Status.OK.getStatusCode(), cfuture.get().status());
  }

  @Test
  public void testHandelSuccessResponseFailure() throws ExecutionException, InterruptedException {
    Response response = new Response();
    BaseController controller = new BaseController();
    CompletionStage<Result> future = controller.handelSuccessResponse(response);
    CompletableFuture<Result> cfuture = (CompletableFuture<Result>) future;
    assertNotEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), cfuture.get().status());
  }

  @Test
  public void testHandelFailureResponseSuccess() throws ExecutionException, InterruptedException {

    Response response = new Response();
    response.put(UserOrgJsonKey.ERROR, true);
    response.put(UserOrgJsonKey.MESSAGE, ResponseCode.internalError.getErrorMessage());
    BaseController controller = new BaseController();
    CompletionStage<Result> future = controller.handelFailureResponse(response);
    CompletableFuture<Result> cfuture = (CompletableFuture<Result>) future;
    assertEquals(Status.BAD_REQUEST.getStatusCode(), cfuture.get().status());
  }

  @Test
  public void testHandelFailureResponseFailure() throws ExecutionException, InterruptedException {
    Response response = new Response();
    CompletionStage<Result> future = controllerObject.handelSuccessResponse(response);
    CompletableFuture<Result> cfuture = (CompletableFuture<Result>) future;
    assertNotEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), cfuture.get().status());
  }
}
