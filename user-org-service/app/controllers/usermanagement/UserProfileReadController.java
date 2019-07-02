package controllers.usermanagement;

import controllers.BaseController;
import java.util.concurrent.CompletionStage;

import org.sunbird.request.Request;
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
  public CompletionStage<Result> getProfileSupportedSocialMediaTypes() {
    startTrace("getProfileSupportedSocialMediaTypes");
    CompletionStage<Result> response = handelRequest();
    endTrace("getProfileSupportedSocialMediaTypes");
    return response;
  }

  /**
   * This method is used to set the profile visibility of user
   *
   * @return Return a CompletionsTAGE OF set user profile visibility Api Result
   */
  public CompletionStage<Result> setProfileVisibility() {

    startTrace("setProfileVisibility");
    CompletionStage<Result> response = handelRequest();
    endTrace("setProfileVisibility");
    return response;
  }

  /**
   * This method is used to get the user with their respective types
   *
   * @return Return a CompletionsTAGE OF get user by type Api Result
   */
  public CompletionStage<Result> getUserTypes() {
    startTrace("getUserTypes");
    CompletionStage<Result> response = handelRequest();
    endTrace("getUserTypes");
    return response;
  }

  /**
   * This method is used to get user by Id
   *
   * @return Return a CompletionsTAGE OF get user by id Api Result
   */
  public CompletionStage<Result> getUserById(String userId) {
    startTrace("getUserById");
    Request request=new Request();
    request.getRequest().put("userId",userId);
    CompletionStage<Result> response = handleRequest(request,"readUserById");
    endTrace("getUserById");
    return response;
  }

  /**
   * This method is used to get user by id but some extra fields appear in the response like skills
   * etc.
   *
   * @return Return a promise of user response
   */
  public CompletionStage<Result> getUserByIdV2(String userId) {
    startTrace("getUserByIdV2");
    CompletionStage<Result> response = handelRequest();
    endTrace("getUserByIdV2");
    return response;
  }

  /**
   * This action method we used to retrive user details on the basis of login Id
   *
   * @return CompletionStage of get User by login Id api result
   */
  public CompletionStage<Result> getUserByLoginId() {
    startTrace("getUserByLoginId");
    CompletionStage<Result> response = handelRequest();
    endTrace("getUserByLoginId");
    return response;
  }

  /**
   * This action method we used to retrive user details on the basis of object.
   *
   * @return CompletionStage of get User by Key api result
   */
  public CompletionStage<Result> getUserByKey(String idType, String id) {
    startTrace("getUserByKey");
    CompletionStage<Result> response = handelRequest();
    endTrace("getUserByKey");
    return response;
  }

  /**
   * This method is used to search user on the basis of filters
   *
   * @return Return a promise of user response
   */

  public CompletionStage<Result> searchUser() {
    startTrace("searchUser");
    CompletionStage<Result> response = handleRequest(request(),null,"searchUser");
    endTrace("searchUser");
    return response;
  }
}
