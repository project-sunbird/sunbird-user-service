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
    private static final String TAG="HealthController";

    /**
     * This action method is responsible for checking Health.
     *
     * @return a CompletableFuture of success response
     */

    public CompletionStage<Result> getHealth() {
        printProjectLogs(TAG,"getHealth",true);
        CompletionStage<Result> response = handelRequest();
        printProjectLogs(TAG,"getHealth",false);
        return response;
    }


    /**
     * This action method is responsible to check user-service health
     *
     * @return a CompletableFuture of success response
     */
    public CompletionStage<Result> getUserOrgServiceHealth(String health) {
        printProjectLogs(TAG,"getHealth",true);
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete(getDummyResponse());
        printProjectLogs(TAG,"getHealth",false);
        return health.equalsIgnoreCase(userOrgServiceName) ? cf.thenApplyAsync(Results::ok) : cf.thenApplyAsync(Results::badRequest);

    }


}

