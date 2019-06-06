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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * This  is a testClass for testing UserProfileReadController
 */
public class UserProfileReadControllerTest {
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
    public void testGetProfileSupportedSocialMediaTypesSuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        Result result = testHelper.performTest("/v1.3/user/mediatype/list", "GET", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testGetProfileSupportedSocialMediaTypesFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        Result result = testHelper.performTest("/v1.3/user/mediatype/list", "GET", reqMap, headerMap);
        assertFalse(testHelper.getResponseStatus(result) == 400);
    }

    @Test
    public void testSetProfileVisibilitySuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("status", "1");
        Result result = testHelper.performTest("/v1.3/user/profile/visibility", "POST", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testSetProfileVisibilityFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("status", "1");
        Result result = testHelper.performTest("/v1.3/user/profile/visibility", "POST", reqMap, headerMap);
        assertFalse(testHelper.getResponseStatus(result) == 400);
    }

    @Test
    public void testGetUserTypesSuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        Result result = testHelper.performTest("/v1.3/user/type/list", "GET", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }

    @Test
    public void testGetUserTypesFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        Result result = testHelper.performTest("/v1.3/user/type/list", "GET", reqMap, headerMap);
        assertFalse(testHelper.getResponseStatus(result) == 400);
    }

    @Test
    public void testGetUserByIdSuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        Result result = testHelper.performTest("/v1.3/user/read/f:1223:233", "GET", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testGetUserByIdFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        Result result = testHelper.performTest("/v1.3/user/read/f:1223:233", "GET", reqMap, headerMap);
        assertFalse(testHelper.getResponseStatus(result) == 400);
    }

    @Test
    public void testGetUserByIdV2Success() {
        Map<String, Object> reqMap = new HashMap<>();
        Result result = testHelper.performTest("/v2.3/user/read/f:1223:233", "GET", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testGetUserByIdV2Failure() {
        Map<String, Object> reqMap = new HashMap<>();
        Result result = testHelper.performTest("/v2.3/user/read/f:1223:233", "GET", reqMap, headerMap);
        assertFalse(testHelper.getResponseStatus(result) == 400);
    }

    @Test
    public void testGetUserByLoginIdSuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("loginId", "12355");
        Result result = testHelper.performTest("/v1.3/user/getuser", "POST", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testGetUserByLoginIdFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("loginId", "12355");
        Result result = testHelper.performTest("/v1.3/user/getuser", "POST", reqMap, headerMap);
        assertFalse(testHelper.getResponseStatus(result) == 400);
    }

    @Test
    public void testGetUserByKeySuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        Result result = testHelper.performTest("/v1.3/user/get/userName/demo", "GET", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testGetUserByKeyFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        Result result = testHelper.performTest("/v1.3/user/get/userName/demo", "POST", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 404);
    }

    @Test
    public void testSearchUserSuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("id", "123555");
        Result result = testHelper.performTest("/v1.3/user/search", "POST", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testSearchUserFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("id", "123555");
        Result result = testHelper.performTest("/v1.3/user/search", "GET", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 404);
    }

    @Test
    public void testSearchPrivateUserSuccess() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("id", "123555");
        Result result = testHelper.performTest("/private/user/v1.3/search", "POST", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 200);
    }
    @Test
    public void testSearchPrivateUserFailure() {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("id", "123555");
        Result result = testHelper.performTest("/private/user/v1.3/search", "GET", reqMap, headerMap);
        assertTrue(testHelper.getResponseStatus(result) == 404);
    }
}