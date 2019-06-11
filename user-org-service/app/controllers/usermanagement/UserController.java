package controllers.usermanagement;

import controllers.BaseController;

import java.util.concurrent.CompletionStage;

import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import play.mvc.*;

/**
 * This controller is used to handel request related to user based updation and creation.
 * CompletionStage: A stage of a possibly asynchronous computation, that performs an action or
 * computes a value when another CompletionStage completes CompletableFuture: A Future that may be
 * explicitly completed (setting its value and status), and may be used as a CompletionStage,
 * supporting dependent functions and actions that trigger upon its completion.
 */
public class UserController extends BaseController {

    /**
     * This method is used to create user
     *
     * @return Return a promise of created userResponse
     */
    public CompletionStage<Result> createUser() {
        ProjectLogger.log(
                "UserController :createUser : request reached to User controller with request " + request().body(), LoggerEnum.INFO.name());

        return handelRequest();
    }

    /**
     * This method is used to create user in this method userName is not mandatory
     *
     * @return Return a promise of created userResponse
     */
    public CompletionStage<Result> createUserV2() {
        ProjectLogger.log(
                "UserController :createUserV2 : request reached to User controller with request " + request().body(), LoggerEnum.INFO.name());
        return handelRequest();
    }
}
