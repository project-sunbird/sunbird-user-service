package controllers.healthmanager;

import controllers.BaseController;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
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

    private static final String userOrgServiceName = "user-org-service";

    /**
     * This action method is responsible for checking Health.
     *
     * @return a CompletableFuture of success response
     */

    public CompletionStage<Result> getHealth() {

        ProjectLogger.log(
                "HealthController :getHealth() : in HealthController ", LoggerEnum.INFO.name());

        return handelRequest();
    }


    /**
     * This action method is responsible to check user-service health
     *
     * @return a CompletableFuture of success response
     */
    public CompletionStage<Result> getUserOrgServiceHealth(String health) {
        ProjectLogger.log(
                "HealthController :getUserOrgServiceHealth : got request params health :"+ health, LoggerEnum.INFO.name());

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete(getDummyResponse());
        return health.equalsIgnoreCase(userOrgServiceName) ? cf.thenApplyAsync(Results::ok) : cf.thenApplyAsync(Results::badRequest);
    }


}

