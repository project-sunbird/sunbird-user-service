package controllers.usermanagement;

import controllers.BaseController;
import java.util.concurrent.CompletionStage;

import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import play.mvc.Result;

/**
 * This controller is used to handel the user socialMedia accounts information * CompletionStage: A
 * stage of a possibly asynchronous computation, that performs an action or computes a value when
 * another CompletionStage completes
 */
public class UserProfileReadController extends BaseController {

  /**
   * This method is used get user SocialMedia type as list
   *
   * @return a CompletionStage of user Medial Type info Api Result.
   */
  public CompletionStage<Result> getProfileSupportedSocialMediaTypes()
  {
    ProjectLogger.log(
            "UserProfileReadController :getProfileSupportedSocialMediaTypes : request reached to UserProfileRead controller with request " + request().body(), LoggerEnum.INFO.name());
    return handelRequest();
  }

  /**
   * This method is used to set the profile visibility of user
   *
   * @return Return a CompletionsTAGE OF set user profile visibility Api Result
   */
  public CompletionStage<Result> setProfileVisibility() {

    ProjectLogger.log(
            "UserProfileReadController :setProfileVisibility : request reached to UserProfileRead controller with request " + request().body(), LoggerEnum.INFO.name());
    return handelRequest();
  }

  /**
   * This method is used to get the user with their respective types
   *
   * @return Return a CompletionsTAGE OF get user by type Api Result
   */
  public CompletionStage<Result> getUserTypes() {
    ProjectLogger.log(
            "UserProfileReadController :getUserTypes : request reached to UserProfileRead controller with request " + request().body(), LoggerEnum.INFO.name());

    return handelRequest();
  }

  /**
   * This method is used to get user by Id
   *
   * @return Return a CompletionsTAGE OF get user by id Api Result
   */
  public CompletionStage<Result> getUserById(String userId) {
    ProjectLogger.log(
            "UserProfileReadController :getUserById : request reached to UserProfileRead controller with request " + request().body(), LoggerEnum.INFO.name());
    return handelRequest();
  }

  /**
   * This method is used to get user by id but some extra fields appear in the response like skills
   * etc.
   *
   * @return Return a promise of user response
   */
  public CompletionStage<Result> getUserByIdV2(String userId)
  {
    ProjectLogger.log(
            "UserProfileReadController :getUserByIdV2 : request reached to UserProfileRead controller with request " + request().body(), LoggerEnum.INFO.name());
    return handelRequest();
  }

  /**
   * This action method we used to retrive user details on the basis of login Id
   *
   * @return CompletionStage of get User by login Id api result
   */
  public CompletionStage<Result> getUserByLoginId() {
    ProjectLogger.log(
            "UserProfileReadController :getUserByLoginId : request reached to UserProfileRead controller with request " + request().body(), LoggerEnum.INFO.name());
    return handelRequest();
  }

  /**
   * This action method we used to retrive user details on the basis of object.
   *
   * @return CompletionStage of get User by Key api result
   */
  public CompletionStage<Result> getUserByKey(String idType, String id) {
    ProjectLogger.log(
            "UserProfileReadController :getUserByKey : request reached to UserProfileRead controller with request " + request().body(), LoggerEnum.INFO.name());
    return handelRequest();
  }

  /**
   * This method is used to search user on the basis of filters
   *
   * @return Return a promise of user response
   */
  public CompletionStage<Result> searchUser() {

    ProjectLogger.log(
            "UserProfileReadController :searchUser: request reached to UserProfileRead controller with request " + request().body(), LoggerEnum.INFO.name());
    return handelRequest();
  }
}
