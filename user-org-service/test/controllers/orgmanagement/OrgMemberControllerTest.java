package controllers.orgmanagement;

import static org.junit.Assert.assertTrue;

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

public class OrgMemberControllerTest {
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
    testHelper = null;
    app = null;
    headerMap = null;
  }

  @Test
  public void testAddMemberToOrgSuccess() {
    Result result =
        testHelper.performTest("/v1.3/org/member/add", "POST", getOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
  }

  @Test
  public void testAddMemberToFailure() {
    Result result =
        testHelper.performTest("/v1.3/org/member/add", "GET", getOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());
  }

  @Test
  public void testRemoveMemberToOrgSuccess() {
    Result result =
        testHelper.performTest("/v1.3/org/member/remove", "POST", getOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
  }

  @Test
  public void testRemoveMemberToFailure() {
    Result result =
        testHelper.performTest("/v1.3/org/member/remove", "GET", getOrgRequest(), headerMap);
    assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());
  }

  private Map<String, Object> getOrgRequest() {
    Map<String, Object> map = new HashMap<>();
    return map;
  }
}
