package controllers.usermanagement;

import static org.junit.Assert.*;

import controllers.BaseControllerTest;
import controllers.TestHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.mvc.Result;
import play.test.Helpers;

/** This is a test Class for UserProfileUpdateController. */
public class UserProfileUpdateControllerTest extends BaseControllerTest {
  TestHelper testHelper;
  public static Application app;
  public static Map<String, String[]> headerMap;

  @Before
  public void setUp() throws Exception {

    testHelper = new TestHelper();
    app = Helpers.fakeApplication();
    Helpers.start(app);
    headerMap = testHelper.getHeaderMap();
  }

  @After
  public void tearDown() throws Exception {
    headerMap = null;
    app = null;
    testHelper = null;
  }

  @Test
  public void testUpdateLoginTimeSuccess() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("loginTime", "11:20");
    Result result =
        testHelper.performTest("/v1.3/user/update/logintime", "PATCH", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
  }

  @Test
  public void testUpdateLoginTimeFailure() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("loginTime", "11:20");
    Result result =
        testHelper.performTest("/v1.3/user/update/logintime", "PATCH", reqMap, headerMap);
    assertFalse(testHelper.getResponseStatus(result) == Status.BAD_REQUEST.getStatusCode());
  }

  @Test
  public void testAssignRolesSuccess() {
    List<String> roles = new ArrayList<>();
    roles.add("public");
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("roles", roles);
    Result result = testHelper.performTest("/v1.3/user/assign/role", "POST", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
  }

  @Test
  public void testAssignRolesFailure() {
    Map<String, Object> reqMap = new HashMap<>();
    Result result = testHelper.performTest("/v1.3/user/assign/role", "GET", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void testUpdateUserSuccess() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("userName", "updatedDemo");
    Result result = testHelper.performTest("/v1.3/user/update", "PATCH", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
  }

  @Test
  public void testUpdateUserFailure() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("userName", "updatedDemo");
    Result result = testHelper.performTest("/v1.3/user/update", "POST", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void testAssignPrivateRolesSuccess() {
    List<String> roles = new ArrayList<>();
    roles.add("public");
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("roles", roles);
    Result result =
        testHelper.performTest("/private/user/v1.3/assign/role", "POST", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
  }

  @Test
  public void testAssignPrivateRolesFailure() {
    List<String> roles = new ArrayList<>();
    roles.add("public");
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("roles", roles);
    Result result =
        testHelper.performTest("/private/user/v1.3/assign/role", "GET", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void testUpdatePrivateUserSuccess() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("userName", "updatedDemo");
    Result result = testHelper.performTest("/private/user/v1.3/update", "PATCH", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
  }

  @Test
  public void testUpdatePrivateUserFailure() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("userName", "updatedDemo");
    Result result = testHelper.performTest("/private/user/v1.3/update", "GET", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());
  }
}
