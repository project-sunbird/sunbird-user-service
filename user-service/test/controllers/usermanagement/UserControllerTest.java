package controllers.usermanagement;

import controllers.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.mvc.Result;
import play.test.Helpers;
import javax.ws.rs.core.Response.Status;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertTrue;

public class UserControllerTest {
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
    public void testSuccessCreateUserSuccess() {
        Result result = testHelper.performTest("/v1.3/user/create", "POST", getCreateUserRequest(), headerMap);
        assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());

    }
    @Test
    public void testSuccessCreateUserFailure() {
        Result result = testHelper.performTest("/v1.3/user/create", "GET", getCreateUserRequest(), headerMap);
        assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());

    }

    @Test
    public void testSuccessCreateUserV2Success() {
        Result result = testHelper.performTest("/v2.3/user/create", "POST", getCreateUserRequest(), headerMap);
        assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());

    }
    @Test
    public void testSuccessCreateUserV2Failure() {
        Result result = testHelper.performTest("/v2.3/user/create", "GET", getCreateUserRequest(), headerMap);
        assertTrue(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode());

    }



    public Map<String, Object> getCreateUserRequest() {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "demo");
        return map;

    }


}