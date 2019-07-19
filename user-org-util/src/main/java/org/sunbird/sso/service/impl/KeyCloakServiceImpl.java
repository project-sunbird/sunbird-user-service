package org.sunbird.sso.service.impl;

import java.security.PublicKey;
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
      ProjectLogger.log(
          "KeyCloakServiceImpl:verifyToken sso public key value " + publicKey,
          LoggerEnum.INFO.name());
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
}
