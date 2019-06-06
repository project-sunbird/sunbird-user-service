package controllers.tac;

import play.mvc.Http;
import play.mvc.Result;
import controllers.BaseController;
import play.mvc.Results;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * This controller will handle all request related to user terms and conditions.
 */
public class UserTncController extends BaseController {

    /**
     * This action method is used to accept the agreement when user login for first time.
     * @return success response
     */
    public CompletionStage<Result> acceptTnc() {
        Http.RequestBody requestBody = request().body();
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete(getDummyResponse());
        return cf.thenApplyAsync(Results::ok);
    }


}
