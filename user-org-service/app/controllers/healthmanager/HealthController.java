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
    startTrace("getHealth");
    CompletionStage<Result> response = handelRequest();
    endTrace("getHealth");
    return response;
  }

  /**
   * This action method is responsible to check user-service health
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> getUserOrgServiceHealth(String health) {
    startTrace("getUserOrgServiceHealth");
    CompletableFuture<String> cf = new CompletableFuture<>();
    cf.complete(getDummyResponse());
    endTrace("getUserOrgServiceHealth");
    return health.equalsIgnoreCase(userOrgServiceName)
        ? cf.thenApplyAsync(Results::ok)
        : cf.thenApplyAsync(Results::badRequest);
  }
}
