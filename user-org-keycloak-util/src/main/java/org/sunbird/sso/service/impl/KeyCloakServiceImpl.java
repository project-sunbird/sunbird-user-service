package org.sunbird.sso.service.impl;

import static java.util.Arrays.asList;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.RSATokenVerifier;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.sunbird.exception.AuthorizationException;
import org.sunbird.exception.ProjectCommonException;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.Localizer;
import org.sunbird.exception.message.ResponseCode;
import org.sunbird.sso.model.User;
import org.sunbird.sso.service.ISSOService;
import org.sunbird.sso.util.KeyCloakConnectionProvider;
import org.sunbird.sso.util.SSOConstant;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.PropertiesCache;

/**
 * Single sign out service implementation with Key Cloak.
 *
 * @author Manzarul
 */
public class KeyCloakServiceImpl implements ISSOService {

  private Keycloak keycloak = KeyCloakConnectionProvider.getConnection();
  private static PublicKey SSO_PUBLIC_KEY = null;

  public PublicKey getPublicKey() {
    if (null == SSO_PUBLIC_KEY) {
      SSO_PUBLIC_KEY =
          new KeyCloakRsaKeyFetcher()
              .getPublicKeyFromKeyCloak(
                  KeyCloakConnectionProvider.SSO_URL, KeyCloakConnectionProvider.SSO_REALM);
    }
    return SSO_PUBLIC_KEY;
  }

  @Override
  public String verifyToken(String accessToken) throws Exception {
    try {
      PublicKey publicKey = getPublicKey();
      if (publicKey == null) {
        ProjectLogger.log(
            "KeyCloakServiceImpl: SSO_PUBLIC_KEY is NULL. Keycloak server may need to be started. Read value from environment variable.",
            LoggerEnum.INFO);
        publicKey = toPublicKey(System.getenv(SSOConstant.SSO_PUBLIC_KEY));
      }
      if (publicKey != null) {
        AccessToken token =
            RSATokenVerifier.verifyToken(
                accessToken,
                publicKey,
                KeyCloakConnectionProvider.SSO_URL
                    + "realms/"
                    + KeyCloakConnectionProvider.SSO_REALM,
                true,
                true);
        ProjectLogger.log(
            token.getId()
                + " "
                + token.issuedFor
                + " "
                + token.getProfile()
                + " "
                + token.getSubject()
                + " Active: "
                + token.isActive()
                + "  isExpired: "
                + token.isExpired()
                + " "
                + token.issuedNow().getExpiration(),
            LoggerEnum.INFO.name());
        String tokenSubject = token.getSubject();
        if (StringUtils.isNotBlank(tokenSubject)) {
          int pos = tokenSubject.lastIndexOf(":");
          return tokenSubject.substring(pos + 1);
        }
        return token.getSubject();
      } else {
        ProjectLogger.log(
            "KeyCloakServiceImpl:verifyToken: SSO_PUBLIC_KEY is NULL.", LoggerEnum.ERROR.name());
        throw new ProjectCommonException.ClientError(
            IResponseMessage.INVALID_REQUESTED_DATA,
            Localizer.getInstance().getMessage(IResponseMessage.INVALID_REQUESTED_DATA, null),
            ResponseCode.CLIENT_ERROR.getCode());
      }
    } catch (Exception e) {
      ProjectLogger.log(
          "KeyCloakServiceImpl:verifyToken: Exception occurred with message = " + e.getMessage(),
          LoggerEnum.ERROR);
      throw new AuthorizationException.Unauthorized(
          IResponseMessage.INTERNAL_ERROR,
          Localizer.getInstance().getMessage(IResponseMessage.INTERNAL_ERROR, null),
          ResponseCode.UNAUTHORIZED.getCode());
    }
  }

  /**
   * This method will generate Public key form keycloak realm publickey String
   *
   * @param publicKeyString String
   * @return PublicKey
   */
  private PublicKey toPublicKey(String publicKeyString) {
    try {
      byte[] publicBytes = Base64.getDecoder().decode(publicKeyString);
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return keyFactory.generatePublic(keySpec);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public boolean updatePassword(String userId, String password) {
    try {
      String fedUserId = getFederatedUserId(userId);
      UserResource ur = keycloak.realm(KeyCloakConnectionProvider.SSO_REALM).users().get(fedUserId);
      CredentialRepresentation cr = new CredentialRepresentation();
      cr.setType(CredentialRepresentation.PASSWORD);
      cr.setValue(password);
      ur.resetPassword(cr);
      return true;
    } catch (Exception e) {
      ProjectLogger.log(
          "KeyCloakServiceImpl:updatePassword: Exception occurred with error message = " + e,
          LoggerEnum.ERROR.name());
    }
    return false;
  }

  @Override
  public boolean updateUser(User user) throws Exception {
    String fedUserId = getFederatedUserId(user.getId());
    UserRepresentation ur = null;
    UserResource resource = null;
    try {
      resource = keycloak.realm(KeyCloakConnectionProvider.SSO_REALM).users().get(fedUserId);
      ur = resource.toRepresentation();
      if (ur == null) {
        ProjectLogger.log(
            "KeyCloakServiceImpl:updateUser: user not found with id " + fedUserId,
            LoggerEnum.INFO.name());
        return false;
      }
      mapUserTOKeyCloakUser(user, ur);
      resource.update(ur);
    } catch (Exception ex) {
      ProjectLogger.log(
          "KeyCloakServiceImpl:updateUser: update failure" + ex, LoggerEnum.ERROR.name());
      throw new ProjectCommonException.ClientError(
          IResponseMessage.INVALID_REQUESTED_DATA,
          Localizer.getInstance().getMessage(IResponseMessage.INVALID_REQUESTED_DATA, null),
          ResponseCode.CLIENT_ERROR.getCode());
    }
    return true;
  }

  /**
   * Method to remove the user on basis of user id.
   *
   * @param request Map
   * @return boolean true if success otherwise false .
   */
  @Override
  public boolean removeUser(String userId) throws Exception {
    Keycloak keycloak = KeyCloakConnectionProvider.getConnection();
    try {
      String fedUserId = getFederatedUserId(userId);
      UserResource resource =
          keycloak.realm(KeyCloakConnectionProvider.SSO_REALM).users().get(fedUserId);
      if (resource != null) {
        resource.remove();
      } else {
        ProjectLogger.log(
            "KeyCloakServiceImpl:removeUser: user not found " + fedUserId, LoggerEnum.INFO.name());
        return false;
      }
    } catch (Exception ex) {
      ProjectLogger.log(
          "KeyCloakServiceImpl:removeUser: remove user failure" + ex, LoggerEnum.ERROR.name());
      throw new ProjectCommonException.ClientError(
          IResponseMessage.INTERNAL_ERROR,
          Localizer.getInstance().getMessage(IResponseMessage.INTERNAL_ERROR, null),
          ResponseCode.SERVER_ERROR.getCode());
    }
    return true;
  }

  /**
   * Method to deactivate the user on basis of user id.
   *
   * @param request Map
   * @return boolean true if success otherwise false .
   */
  @Override
  public boolean deactivateUser(String userId) {
    return makeUserActiveOrInactive(userId, false);
  }

  /**
   * Method to activate the user on basis of user id.
   *
   * @param request Map
   * @return boolean true if success otherwise false .
   */
  @Override
  public boolean activateUser(String userId) {
    return makeUserActiveOrInactive(userId, true);
  }

  /**
   * This method will take userid and boolean status to update user status
   *
   * @param userId String
   * @param status boolean
   * @throws ProjectCommonException
   */
  private boolean makeUserActiveOrInactive(String userId, boolean status) {
    try {
      String fedUserId = getFederatedUserId(userId);
      validateUserId(fedUserId);
      Keycloak keycloak = KeyCloakConnectionProvider.getConnection();
      UserResource resource =
          keycloak.realm(KeyCloakConnectionProvider.SSO_REALM).users().get(fedUserId);
      if (resource == null) {
        return false;
      }
      UserRepresentation ur = resource.toRepresentation();
      ur.setEnabled(status);
      resource.update(ur);
    } catch (Exception e) {
      ProjectLogger.log(
          "KeyCloakServiceImpl:makeUserActiveOrInactive: call fails with error" + e,
          LoggerEnum.ERROR.name());
      return false;
    }
    return true;
  }

  /**
   * This method will check userId value, if value is null or empty then it will throw
   * ProjectCommonException
   *
   * @param userId String
   * @throws ProjectCommonException
   */
  private void validateUserId(String userId) throws Exception {
    if (StringUtils.isBlank(userId)) {
      ProjectLogger.log(
          "KeyCloakServiceImpl:validateUserId: userId is either null or empty" + userId,
          LoggerEnum.ERROR.name());
      throw new ProjectCommonException.ClientError(
          IResponseMessage.INVALID_REQUESTED_DATA,
          Localizer.getInstance().getMessage(IResponseMessage.INVALID_REQUESTED_DATA, null),
          ResponseCode.CLIENT_ERROR.getCode());
    }
  }

  @Override
  public boolean isEmailVerified(String userId) {
    String fedUserId = getFederatedUserId(userId);
    UserResource resource =
        keycloak.realm(KeyCloakConnectionProvider.SSO_REALM).users().get(fedUserId);
    if (resource == null) {
      return false;
    }
    return resource.toRepresentation().isEmailVerified();
  }

  @Override
  public boolean updateEmailVerified(String userId, boolean isEmailVerified) {
    String fedUserId = getFederatedUserId(userId);
    UserResource resource =
        keycloak.realm(KeyCloakConnectionProvider.SSO_REALM).users().get(fedUserId);
    UserRepresentation user = resource.toRepresentation();
    Map<String, List<String>> map = user.getAttributes();
    List<String> list = new ArrayList<>();
    list.add(isEmailVerified + "");
    if (map == null) {
      map = new HashMap<>();
    }
    map.put(SSOConstant.EMAIL_VERIFIED_UPDATED, list);
    user.setAttributes(map);
    resource.update(user);
    return true;
  }

  @Override
  public String getLastLoginTime(String userId) {
    String lastLoginTime = null;
    try {
      String fedUserId = getFederatedUserId(userId);
      UserResource resource =
          keycloak.realm(KeyCloakConnectionProvider.SSO_REALM).users().get(fedUserId);
      UserRepresentation ur = resource.toRepresentation();
      Map<String, List<String>> map = ur.getAttributes();
      if (map == null) {
        map = new HashMap<>();
      }
      List<String> list = map.get(SSOConstant.LAST_LOGIN_TIME);
      if (list != null && !list.isEmpty()) {
        lastLoginTime = list.get(0);
      }
    } catch (Exception e) {
      ProjectLogger.log(e.getMessage(), e);
    }
    return lastLoginTime;
  }

  @Override
  public boolean addUserLoginTime(String userId) {
    boolean response = true;
    try {
      String fedUserId = getFederatedUserId(userId);
      UserResource resource =
          keycloak.realm(KeyCloakConnectionProvider.SSO_REALM).users().get(fedUserId);
      UserRepresentation ur = resource.toRepresentation();
      Map<String, List<String>> map = ur.getAttributes();
      List<String> list = new ArrayList<>();
      if (map == null) {
        map = new HashMap<>();
      }
      List<String> currentLogTime = map.get(SSOConstant.CURRENT_LOGIN_TIME);
      if (currentLogTime == null || currentLogTime.isEmpty()) {
        currentLogTime = new ArrayList<>();
        currentLogTime.add(Long.toString(System.currentTimeMillis()));
      } else {
        list.add(currentLogTime.get(0));
        currentLogTime.clear();
        currentLogTime.add(0, Long.toString(System.currentTimeMillis()));
      }
      map.put(SSOConstant.CURRENT_LOGIN_TIME, currentLogTime);
      map.put(SSOConstant.LAST_LOGIN_TIME, list);
      ur.setAttributes(map);
      resource.update(ur);
    } catch (Exception e) {
      ProjectLogger.log(e.getMessage(), e);
      response = false;
    }
    return response;
  }

  private String getFederatedUserId(String userId) {
    if (StringUtils.isNotBlank(
        PropertiesCache.getInstance()
            .readProperty(SSOConstant.SUNBIRD_KEYCLOAK_USER_FEDERATION_PROVIDER_ID))) {
      return String.join(
          ":",
          "f",
          PropertiesCache.getInstance()
              .readProperty(SSOConstant.SUNBIRD_KEYCLOAK_USER_FEDERATION_PROVIDER_ID),
          userId);
    }
    ProjectLogger.log(
        "KeyCloakServiceImpl:getFederatedUserId fedration id is not set." + userId,
        LoggerEnum.INFO.name());
    return userId;
  }

  @Override
  public void setRequiredAction(String userId, String requiredAction) {
    String fedUserId = getFederatedUserId(userId);
    UserResource resource =
        keycloak.realm(KeyCloakConnectionProvider.SSO_REALM).users().get(fedUserId);

    UserRepresentation userRepresentation = resource.toRepresentation();
    userRepresentation.setRequiredActions(asList(requiredAction));
    if (SSOConstant.VERIFY_EMAIL.equalsIgnoreCase(requiredAction)) {
      userRepresentation.setEmailVerified(false);
    }
    resource.update(userRepresentation);
  }

  @Override
  public User getUserById(String userId) {
    String fedUserId = getFederatedUserId(userId);
    try {
      UserResource resource =
          keycloak.realm(KeyCloakConnectionProvider.SSO_REALM).users().get(fedUserId);
      UserRepresentation ur = resource.toRepresentation();
      return mapKeyCloakUserResponseTOUserModel(ur);
    } catch (Exception e) {
      ProjectLogger.log(
          "KeyCloakServiceImpl:getUsernameById: User not found for userId = "
              + userId
              + " error message = "
              + e.getMessage(),
          e);
    }
    ProjectLogger.log(
        "KeyCloakServiceImpl:getUsernameById: User not found for userId = " + userId,
        LoggerEnum.INFO.name());
    return null;
  }

  /**
   * This method will convert keycloak user to sunbird user model.
   *
   * @param userRepresentation UserRepresentation
   * @return User
   */
  private User mapKeyCloakUserResponseTOUserModel(UserRepresentation userRepresentation) {
    User user = new User();
    user.setId(userRepresentation.getId());
    user.setFirstName(userRepresentation.getFirstName());
    user.setLastName(userRepresentation.getLastName());
    user.setEmail(userRepresentation.getEmail());
    user.setEmailVerified(userRepresentation.isEmailVerified());
    user.setUserName(userRepresentation.getUsername());
    Map<String, List<String>> extData = userRepresentation.getAttributes();
    if (extData != null && extData.containsKey(SSOConstant.PHONE)) {
      List<String> phones = extData.get(SSOConstant.PHONE);
      if (phones != null && phones.size() == 1) {
        user.setPhone(phones.get(0));
        user.setPhoneVerified(true);
      }
      List<String> countryCode = extData.get(SSOConstant.COUNTRY_CODE);
      if (countryCode != null && countryCode.size() == 1) {
        user.setCountryCode(countryCode.get(0));
      }
    }
    return user;
  }

  /**
   * This method will convert sunbird user model to keycloak data model.
   *
   * @param user User
   * @param userRepresentation UserRepresentation
   * @return UserRepresentation
   */
  private UserRepresentation mapUserTOKeyCloakUser(
      User user, UserRepresentation userRepresentation) {
    if (user.getFirstName() != null) {
      userRepresentation.setFirstName(user.getFirstName());
    }
    if (user.getEmail() != null) {
      userRepresentation.setEmail(user.getEmail());
      userRepresentation.setEmailVerified(user.isEmailVerified());
    }
    if (user.getLastName() != null) {
      userRepresentation.setLastName(user.getLastName());
    }
    if (user.getPhone() != null) {
      Map<String, List<String>> map = userRepresentation.getAttributes();
      List<String> list = new ArrayList<>();
      list.add(user.getPhone());
      if (map == null) {
        map = new HashMap<>();
      }
      map.put(SSOConstant.PHONE, list);
      userRepresentation.setAttributes(map);
    }
    if (user.getCountryCode() == null) {
      user.setCountryCode(
          PropertiesCache.getInstance().readProperty("sunbird_default_country_code"));
      Map<String, List<String>> map = userRepresentation.getAttributes();
      if (map == null) {
        map = new HashMap<>();
      }
      List<String> list = new ArrayList<>();
      list.add(user.getCountryCode());
      map.put(SSOConstant.COUNTRY_CODE, list);
      userRepresentation.setAttributes(map);
    }

    return userRepresentation;
  }
}
