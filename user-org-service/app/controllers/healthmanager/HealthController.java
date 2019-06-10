package controllers.healthmanager;

import controllers.BaseController;
import play.mvc.Result;
import play.mvc.Results;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


/**
 * This controller class will responsible to check health of the services.
 *
 * @author Anmol
 */
public class HealthController extends BaseController {


    /**
     * This action method is responsible for checking Health.
     *
     * @return a CompletableFuture of success response
     */

    public CompletionStage<Result> getHealth() {
        return handelRequest();
    }


    /**
     * This action method is responsible to check user-service health
     *
     * @return a CompletableFuture of success response
     */
    public CompletionStage<Result> getUserOrgServiceHealth(String health) {
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete(getDummyResponse());
        return health.equalsIgnoreCase("user-org-service")?cf.thenApplyAsync(Results::ok):cf.thenApplyAsync(Results::badRequest);
    }


}

