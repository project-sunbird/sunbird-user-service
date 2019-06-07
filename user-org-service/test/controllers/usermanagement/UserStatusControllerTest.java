package controllers.usermanagement;

import static org.junit.Assert.*;

import controllers.TestHelper;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.mvc.Result;
import play.test.Helpers;

/** This is a test class for UserStatusController. */
public class UserStatusControllerTest {
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
  public void testBlockUserSuccess() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("status", "0");
    Result result = testHelper.performTest("/v1.3/user/block", "POST", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
  }

  @Test
  public void testBlockUserFailure() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("status", "0");
    Result result = testHelper.performTest("/v1.3/user/block", "GET", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void testUnblockUserSuccess() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("status", "1");
    Result result = testHelper.performTest("/v1.3/user/unblock", "POST", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
  }

  @Test
  public void testUnblockUserFailure() {
    Map<String, Object> reqMap = new HashMap<>();
    reqMap.put("status", "1");
    Result result = testHelper.performTest("/v1.3/user/unblock", "GET", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());
  }
}
