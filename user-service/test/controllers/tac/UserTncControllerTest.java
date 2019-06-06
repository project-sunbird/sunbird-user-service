package controllers.tac;

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

import static org.junit.Assert.*;

public class UserTncControllerTest {
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
    public void testAcceptTncSuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("accept", "yes");
        Result result = testHelper.performTest("/v1.3/user/tnc/accept", "POST", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
    }
    @Test
    public void testAcceptTncFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("accept", "yes");
        Result result = testHelper.performTest("/v1.3/user/tnc/accept", "POST", reqMap, headerMap);
        assertFalse(testHelper.getResponseStatus(result) == Status.BAD_REQUEST.getStatusCode());
    }
}