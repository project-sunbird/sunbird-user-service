package org.sunbird.sso.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sunbird.response.Response;
import org.sunbird.sso.model.User;
import org.sunbird.sso.service.ISSOService;
import org.sunbird.sso.service.SSOServiceFactory;
import org.sunbird.sso.util.KeyCloakConnectionProvider;
import org.sunbird.sso.util.SSOConstant;
import org.sunbird.util.PropertiesCache;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*"})
@PrepareForTest({KeyCloakConnectionProvider.class, KeyCloakServiceImpl.class})
public class KeyCloakServiceImplTest {

  private ISSOService keyCloakService = SSOServiceFactory.getInstance();

  private static Map<String, String> userId = new HashMap<>();
  private static final String userName = UUID.randomUUID().toString().replaceAll("-", "");
  private static Class t = null;
  private static final Map<String, Object> USER_SUCCESS = new HashMap<>();

  static {
    USER_SUCCESS.put("userName", userName);
    USER_SUCCESS.put("password", "password");
    USER_SUCCESS.put("firstName", "A");
    USER_SUCCESS.put("lastName", "B");
    USER_SUCCESS.put(SSOConstant.PHONE, "9870060000");
    USER_SUCCESS.put("email", userName.substring(0, 10));
  }

  private static final Map<String, Object> USER_SAME_EMAIL = new HashMap<>();

  static {
    USER_SAME_EMAIL.put("userName", userName);
    USER_SAME_EMAIL.put("password", "password");
    USER_SAME_EMAIL.put("firstName", "A");
    USER_SAME_EMAIL.put("lastName", "B");
    USER_SAME_EMAIL.put(SSOConstant.PHONE, "9870060000");
    USER_SAME_EMAIL.put("email", userName.substring(0, 10));
  }

  private static UsersResource usersRes = mock(UsersResource.class);

  @BeforeClass
  public static void init() {
    try {
      t = Class.forName("org.sunbird.sso.service.SSOServiceFactory");
    } catch (ClassNotFoundException e) {
    }
    Keycloak kcp = mock(Keycloak.class);
    RealmResource realmRes = mock(RealmResource.class);
    UserResource userRes = mock(UserResource.class);
    UserRepresentation userRep = mock(UserRepresentation.class);
    Response response = mock(Response.class);
    PowerMockito.mockStatic(KeyCloakConnectionProvider.class);
    try {

      doReturn(kcp).when(KeyCloakConnectionProvider.class, "getConnection");
      doReturn(realmRes).when(kcp).realm(Mockito.anyString());
      doReturn(usersRes).when(realmRes).users();
      doReturn(userRes).when(usersRes).get(userId.get("userId"));
      doReturn(userRep).when(userRes).toRepresentation();
      doNothing().when(userRes).update(Mockito.any(UserRepresentation.class));

      doNothing().when(userRes).remove();

      Map<String, Object> map = new HashMap<>();
      map.put(
          SSOConstant.LAST_LOGIN_TIME, Arrays.asList(String.valueOf(System.currentTimeMillis())));
      doReturn(map).when(userRep).getAttributes();
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail(
          "Failed in initialization of mock rules, underlying error: " + e.getLocalizedMessage());
    }
  }

  @Test
  public void testNewInstanceSucccess() {
    Exception exp = null;
    try {
      Constructor<SSOServiceFactory> constructor = t.getDeclaredConstructor();
      constructor.setAccessible(true);
      SSOServiceFactory application = constructor.newInstance();
      Assert.assertNotNull(application);
    } catch (Exception e) {
      exp = e;
    }
    Assert.assertNull(exp);
  }

  @Test
  public void testUserUpdateTestSuccessWithAllData() throws Exception {
    User user = new User();
    user.setId(userId.get("userId"));
    user.setFirstName(userName);
    user.setPhone("9870060000");
    user.setPhoneVerified(true);
    user.setEmail(userName.substring(0, 10));
    user.setEmailVerified(true);
    boolean response = keyCloakService.updateUser(user);
    assertTrue(response);
  }

  @Test
  public void testUpdateUserSuccessWithoutAnyField() throws Exception {
    User user = new User();
    user.setId(userId.get("userId"));
    boolean result = keyCloakService.updateUser(user);
    assertTrue(result);
  }

  @Test
  public void testDeactivateUserSuccess() {
    boolean response = keyCloakService.deactivateUser("123");
    assertFalse(response);
  }

  @Test
  public void testAddUserLoginTimeSuccess() {
    boolean response = keyCloakService.addUserLoginTime(userId.get("userId"));
    Assert.assertEquals(true, response);
  }

  @Test
  public void testGetLastLoginTimeSuccess() {
    String lastLoginTime = keyCloakService.getLastLoginTime(userId.get("userId"));
    Assert.assertNull(lastLoginTime);
  }

  @Test
  public void testIsEmailVerifiedSuccess() {
    boolean response = keyCloakService.isEmailVerified(userId.get("userId"));
    Assert.assertEquals(false, response);
  }

  @Test
  public void testSetEmailVerifiedSuccessWithVerifiedUpdateFalse() {
    keyCloakService.updateEmailVerified(userId.get("userId"), false);
    boolean response = keyCloakService.isEmailVerified(userId.get("userId"));
    assertFalse(response);
  }

  @Test
  public void testSetEmailVerifiedTrueSuccessWithVerifiedTrue() {
    boolean response = keyCloakService.updateEmailVerified(userId.get("userId"), true);
    assertTrue(response);
  }

  @Test
  public void testSetEmailVerifiedSuccessWithVerifiedTrue() {
    boolean response = keyCloakService.updateEmailVerified(userId.get("userId"), true);
    assertTrue(response);
  }

  @Test
  public void testDoPasswordUpdateSuccess() {
    boolean response = keyCloakService.updatePassword(userId.get("userId"), "password");
    Assert.assertEquals(true, response);
  }

  @Test
  public void testGetFederatedUserId()
      throws ClassNotFoundException, InstantiationException, IllegalAccessException,
          NoSuchMethodException, SecurityException, IllegalArgumentException,
          InvocationTargetException {
    KeyCloakServiceImpl.class.getDeclaredMethods();
    Method m = KeyCloakServiceImpl.class.getDeclaredMethod("getFederatedUserId", String.class);
    m.setAccessible(true);
    ISSOService keyCloakService = SSOServiceFactory.getInstance();
    String fedUserId = (String) m.invoke(keyCloakService, "userId");
    if (StringUtils.isNotBlank(
        PropertiesCache.getInstance()
            .readProperty(SSOConstant.SUNBIRD_KEYCLOAK_USER_FEDERATION_PROVIDER_ID))) {
      Assert.assertEquals(
          "f:"
              + PropertiesCache.getInstance()
                  .readProperty(SSOConstant.SUNBIRD_KEYCLOAK_USER_FEDERATION_PROVIDER_ID)
              + ":userId",
          fedUserId);
    } else {
      Assert.assertEquals(fedUserId, "userId");
    }
  }

  @Test
  public void testUpdatePassword() throws Exception {
    boolean updated = keyCloakService.updatePassword(userId.get("userId"), "password");
    Assert.assertTrue(updated);
  }

  @Test
  public void testGetUserByIdSuccess() {
    User user = keyCloakService.getUserById(userId.get("userId"));
    Assert.assertNotNull(user);
  }

  @Test
  public void testGetUserByIdFailure() {
    User user = keyCloakService.getUserById("unknownId");
    Assert.assertNull(user);
  }
}
