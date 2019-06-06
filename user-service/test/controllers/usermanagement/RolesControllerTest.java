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

import static org.junit.Assert.*;

public class RolesControllerTest {

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
    }

    @Test
    public void testGetRolesSuccess() {
        Map<String,Object>reqMap=new HashMap<>();
        Result result = testHelper.performTest("/v1.3/role/read", "GET",reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == Status.OK.getStatusCode());
    }
    @Test
    public void testGetRolesFailure() {
        Map<String,Object>reqMap=new HashMap<>();
        Result result = testHelper.performTest("/v1.3/role/read", "GET",reqMap, headerMap);
        assertFalse(testHelper.getResponseStatus(result) == Status.NOT_FOUND.getStatusCode() );
    }

}