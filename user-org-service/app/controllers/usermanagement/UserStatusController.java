package controllers.usermanagement;

import controllers.BaseController;

import java.util.concurrent.CompletionStage;

import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import play.mvc.Result;

/**
 * This controller is used to change status of user CompletionStage: A stage of a possibly
 * asynchronous computation, that performs an action or computes a value when another
 * CompletionStage completes CompletableFuture: A Future that may be explicitly completed (setting
 * its value and status), and may be used as a CompletionStage, supporting dependent functions and
 * actions that trigger upon its completion.
 */
public class UserStatusController extends BaseController {

    /**
     * This action method is used to block the user
     *
     * @return CompletionStage of block user api result
     */
    public CompletionStage<Result> blockUser() {

        printProjectLogs(UserStatusController.class.getSimpleName(), "blockUser", true);
        CompletionStage<Result> response = handelRequest();
        printProjectLogs(UserStatusController.class.getSimpleName(), "blockUser", false);
        return response;
    }

    /**
     * This action method is used to unblock the user
     *
     * @return CompletionStage of unblock user api result
     */
    public CompletionStage<Result> unblockUser() {

        printProjectLogs(UserStatusController.class.getSimpleName(), "unblockUser", true);
        CompletionStage<Result> response = handelRequest();
        printProjectLogs(UserStatusController.class.getSimpleName(), "unblockUser", false);
        return response;
    }
}
