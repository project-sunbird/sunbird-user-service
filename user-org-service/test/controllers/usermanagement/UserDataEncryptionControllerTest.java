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

/** This is a test class for UserDataEncryptionController */
public class UserDataEncryptionControllerTest {

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
  public void testEncryptSuccess() {
    Map<String, Object> reqMap = new HashMap<>();
    Result result = testHelper.performTest("/v1.3/user/data/encrypt", "POST", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
  }

  @Test
  public void testEncryptFailure() {
    Map<String, Object> reqMap = new HashMap<>();
    Result result = testHelper.performTest("/v1.3/user/data/encrypt", "GET", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void testDecryptSuccess() {
    Map<String, Object> reqMap = new HashMap<>();
    Result result = testHelper.performTest("/v1.3/user/data/decrypt", "POST", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
  }

  @Test
  public void testDecryptFailure() {
    Map<String, Object> reqMap = new HashMap<>();
    Result result = testHelper.performTest("/v1.3/user/data/decrypt", "GET", reqMap, headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());
  }
}
