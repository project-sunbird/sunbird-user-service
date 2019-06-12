package controllers.healthmanager;

import controllers.BaseController;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import play.mvc.Result;
import play.mvc.Results;

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
    printProjectLogs(HealthController.class.getSimpleName(), "getHealth", true);
    CompletionStage<Result> response = handelRequest();
    printProjectLogs(HealthController.class.getSimpleName(), "getHealth", false);
    return response;
  }

  /**
   * This action method is responsible to check user-service health
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> getUserOrgServiceHealth(String health) {
    printProjectLogs(HealthController.class.getSimpleName(), "getHealth", true);
    CompletableFuture<String> cf = new CompletableFuture<>();
    cf.complete(getDummyResponse());
    printProjectLogs(HealthController.class.getSimpleName(), "getHealth", false);
    return health.equalsIgnoreCase(userOrgServiceName)
        ? cf.thenApplyAsync(Results::ok)
        : cf.thenApplyAsync(Results::badRequest);
  }
}
