package controllers;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sunbird.exception.ResponseMessage;
import org.sunbird.response.Response;
import org.sunbird.util.UserOrgJsonKey;
import play.Application;
import play.test.Helpers;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    response.put(UserOrgJsonKey.MESSAGE, ResponseMessage.INTERNAL_ERROR);
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
}
