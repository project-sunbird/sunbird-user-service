/** */
package org.sunbird.sso.service;

import org.sunbird.sso.model.User;

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
   * Method to update user account in keycloak on basis of userId.
   *
   * @param user User
   * @return boolean
   */
  boolean updateUser(User user) throws Exception;

  /**
   * Method to remove user from keycloak account on basis of userId .
   *
   * @param userId String
   * @throws Exception
   * @return boolean
   */
  boolean removeUser(String userId) throws Exception;

  /**
   * This method will check email is verified by user or not.
   *
   * @param userId String
   * @return boolean
   */
  boolean isEmailVerified(String userId);

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

  /**
   * This method will read user last login time from key claok.
   *
   * @param userId String
   * @return String (as epoch value or null)
   */
  String getLastLoginTime(String userId);

  /**
   * This method will add user current login time to keycloak.
   *
   * @param userId String
   * @return boolean
   */
  boolean addUserLoginTime(String userId);

  /**
   * this method will set emailVerified flag of keycloak as true or false
   *
   * @param userId String
   * @param isVerified boolean
   */
  boolean updateEmailVerified(String userId, boolean isVerified);

  /**
   * This method will allow user to update required action.
   *
   * @param userId
   * @param requiredAction
   */
  void setRequiredAction(String userId, String requiredAction);

  /**
   * This method will provide user details based on passed userId.
   *
   * @param userId String
   * @return User or null in case of user not found.
   */
  User getUserById(String userId);
}
