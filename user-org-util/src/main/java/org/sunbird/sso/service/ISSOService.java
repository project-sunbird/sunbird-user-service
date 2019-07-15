/** */
package org.sunbird.sso.service;

/**
 * This interface will have different method related to keycloak
 *
 * @author manzarul
 */
public interface ISSOService {

  /**
   * This method will verify user access token and provide userId if token is valid. in case of
   * invalid access token it will throw ProjectCommon exception with 401.
   *
   * @param token String JWT access token
   * @throws Exception
   * @return String after successful verification it will return userId.
   */
  String verifyToken(String token) throws Exception;

  /**
   * This method will allow user to update password.
   *
   * @param userId String
   * @param password String
   * @return boolean
   */
  boolean updatePassword(String userId, String password);

  /**
   * Method to deactivate user from keycloak , it is like soft delete .
   *
   * @param userId
   * @return
   */
  boolean deactivateUser(String userId);

  /**
   * Method to activate user from keycloak , it is like soft delete .
   *
   * @param request
   * @return
   */
  boolean activateUser(String userId);
}
