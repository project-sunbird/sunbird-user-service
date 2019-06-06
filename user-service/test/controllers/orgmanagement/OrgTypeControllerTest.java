package controllers.orgmanagement;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sunbird.common.models.util.JsonKey;
import org.sunbird.common.request.HeaderParam;

import controllers.TestHelper;
import play.Application;
import play.mvc.Result;
import play.test.Helpers;

public class OrgTypeControllerTest {
  TestHelper testHelper;
  public static Application app;
  public static Map<String, String[]> headerMap;

  @Before
  public void setUp() throws Exception {

    testHelper = new TestHelper();
    app = Helpers.fakeApplication();
    Helpers.start(app);
    headerMap = new HashMap<>();
    headerMap.put(HeaderParam.X_Consumer_ID.getName(), new String[] { "Some consumer ID" });
    headerMap.put(HeaderParam.X_Device_ID.getName(), new String[] { "Some device ID" });
    headerMap.put(HeaderParam.X_Authenticated_Userid.getName(), new String[] { "Some authenticated user ID" });
    headerMap.put(JsonKey.MESSAGE_ID, new String[] { "Some message ID" });
    headerMap.put(HeaderParam.X_APP_ID.getName(), new String[] { "Some app Id" });
  }

  @After
  public void tearDown() throws Exception {
    testHelper = null;
    app = null;
    headerMap = null;

  }

  @Test
  public void testOrgTypeCreateSuccess() {
    Result result = testHelper.performTest("/v1.3/org/type/create", "POST", getOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());

  }

  @Test
  public void testOrgTypeCreateFailure() {
    Result result = testHelper.performTest("/v1.3/org/type/create", "GET", getOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());

  }

  @Test
  public void testOrgTypeUpdateSuccess() {
    Result result = testHelper.performTest("/v1.3/org/type/update", "PATCH", getOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());

  }

  @Test
  public void testOrgTypeUpdateFailure() {
    Result result = testHelper.performTest("/v1.3/org/type/update", "GET", getOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());

  }

  @Test
  public void testOrgTypeListSuccess() {
    Result result = testHelper.performTest("/v1.3/org/type/list", "GET", getOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());

  }

  @Test
  public void testOrgTypeListFailure() {
    Result result = testHelper.performTest("/v1.3/org/type/list", "POST", getOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());

  }


  private Map<String, Object> getOrgRequest() {
    Map<String, Object> map = new HashMap<>();
    return map;
  }

}
