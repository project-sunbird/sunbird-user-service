package controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrgBaseControllerTest {
  BaseController controllerObject;

  @Before
  public void setUp() throws Exception {
    controllerObject = new BaseController();
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
}
