package controllers.usermanagement;

import controllers.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sunbird.common.models.util.JsonKey;
import org.sunbird.common.request.HeaderParam;
import play.Application;
import play.mvc.Result;
import play.test.Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * This is a test Class for UserProfileUpdateController.
 */
public class UserProfileUpdateControllerTest {
    TestHelper testHelper;
    public static Application app;
    public static Map<String, String[]> headerMap;

    @Before
    public void setUp() throws Exception {

        testHelper = new TestHelper();
        app = Helpers.fakeApplication();
        Helpers.start(app);
        headerMap = new HashMap<>();
        headerMap.put(HeaderParam.X_Consumer_ID.getName(), new String[]{"Some consumer ID"});
        headerMap.put(HeaderParam.X_Device_ID.getName(), new String[]{"Some device ID"});
        headerMap.put(
                HeaderParam.X_Authenticated_Userid.getName(), new String[]{"Some authenticated user ID"});
        headerMap.put(JsonKey.MESSAGE_ID, new String[]{"Some message ID"});
        headerMap.put(HeaderParam.X_APP_ID.getName(), new String[]{"Some app Id"});
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
        Result result = testHelper.performTest("/v1.3/user/update/logintime", "PATCH", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testUpdateLoginTimeFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("loginTime", "11:20");
        Result result = testHelper.performTest("/v1.3/user/update/logintime", "PATCH", reqMap, headerMap);
        assertFalse(testHelper.getResponseStatus(result) == 400);
    }

    @Test
    public void testAssignRolesSuccess() {
        List<String> roles = new ArrayList<>();
        roles.add("public");
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("roles", roles);
        Result result = testHelper.performTest("/v1.3/user/assign/role", "POST", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testAssignRolesFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        Result result = testHelper.performTest("/v1.3/user/assign/role", "GET", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 404);
    }

    @Test
    public void testUpdateUserSuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("userName", "updatedDemo");
        Result result = testHelper.performTest("/v1.3/user/update", "PATCH", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testUpdateUserFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("userName", "updatedDemo");
        Result result = testHelper.performTest("/v1.3/user/update", "POST", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 404);
    }

    @Test
    public void testAssignPrivateRolesSuccess() {
        List<String> roles = new ArrayList<>();
        roles.add("public");
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("roles", roles);
        Result result = testHelper.performTest("/private/user/v1.3/assign/role", "POST", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }

    @Test
    public void testAssignPrivateRolesFailure() {
        List<String> roles = new ArrayList<>();
        roles.add("public");
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("roles", roles);
        Result result = testHelper.performTest("/private/user/v1.3/assign/role", "GET", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 404);
    }

    @Test
    public void testUpdatePrivateUserSuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("userName", "updatedDemo");
        Result result = testHelper.performTest("/private/user/v1.3/update", "PATCH", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testUpdatePrivateUserFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("userName", "updatedDemo");
        Result result = testHelper.performTest("/private/user/v1.3/update", "GET", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 404);
    }
}