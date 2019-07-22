package controllers.usermanagement;

import controllers.BaseController;
import java.util.concurrent.CompletionStage;
import org.json.JSONObject;
import org.sunbird.actorOperation.UserActorOperations;
import play.mvc.Result;
import utils.validator.uservalidator.UserCreateModelValidator;

/**
 * This controller is used to handel request related to user based creation. CompletionStage: A
 * stage of a possibly asynchronous computation, that performs an action or computes a value when
 * another CompletionStage completes CompletableFuture: A Future that may be explicitly completed
 * (setting its value and status), and may be used as a CompletionStage, supporting dependent
 * functions and actions that trigger upon its completion.
 */
public class UserController extends BaseController {

  /**
   * This method is used to create user
   *
   * @return Return a promise of created userResponse
   */
  public CompletionStage<Result> createUser() {
    startTrace("createUser");
    JSONObject jsonObject = jsonify(request().body().asJson());
    CompletionStage<Result> response =
        handleRequest(
            request(),
            new UserCreateModelValidator(jsonObject),
            UserActorOperations.CREATE_USER.getOperation());
    endTrace("createUser");
    return response;
  }

  /**
   * This method is used to create user in this method userName is not mandatory
   *
   * @return Return a promise of created userResponse
   */
  public CompletionStage<Result> createUserV2() {
    startTrace("createUserV2");
    CompletionStage<Result> response =
        handleRequest(request(), null, UserActorOperations.CREATE_USER.getOperation());
    endTrace("createUserV2");
    return response;
  }
}
