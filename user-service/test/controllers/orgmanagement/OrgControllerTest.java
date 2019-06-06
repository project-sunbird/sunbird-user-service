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

public class OrgControllerTest {
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
  public void testCreateOrgSuccess() {
    Result result = testHelper.performTest("/v1.3/org/create", "POST", getCreateOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());

  }
  @Test
  public void testCreateOrgFailure() {
    Result result = testHelper.performTest("/v1.3/org/create", "GET", getCreateOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());

  }
  @Test
  public void testUpdateOrgSuccess() {
    Result result = testHelper.performTest("/v1.3/org/update", "PATCH", getCreateOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());

  }
  @Test
  public void testUpdateOrgFailure() {
    Result result = testHelper.performTest("/v1.3/org/update", "GET", getCreateOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());

  }
  @Test
  public void testUpdateOrgStatusSuccess() {
    Result result = testHelper.performTest("/v1.3/org/status/update", "PATCH", getCreateOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());

  }
  @Test
  public void testUpdateOrgstatusFailure() {
    Result result = testHelper.performTest("/v1.3/org/status/update", "GET", getCreateOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());

  }
  @Test
  public void testGetOrgDetailsSuccess() {
    Result result = testHelper.performTest("/v1.3/org/read", "POST", getCreateOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());

  }
  @Test
  public void testGetOrgDetailsFailure() {
    Result result = testHelper.performTest("/v1.3/org/read", "GET", getCreateOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());

  }
  @Test
  public void testOrgSearchSuccess() {
    Result result = testHelper.performTest("/v1.3/org/search", "POST", getCreateOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());

  }
  @Test
  public void testOrgSearchFailure() {
    Result result = testHelper.performTest("/v1.3/org/search", "GET", getCreateOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());

  }

  private Map<String, Object> getCreateOrgRequest() {
    Map<String, Object> map = new HashMap<>();

    return map;
  }

}
