package controllers.orgmanagement;

import controllers.BaseController;
import java.util.concurrent.CompletionStage;
import play.mvc.Result;

/**
 * This Controller is dedicated to organisation type creation , update , listing . CompletionStage:
 * A stage of a possibly asynchronous computation, that performs an action or computes a value when
 * another CompletionStage completes
 */
public class OrgTypeController extends BaseController {

  /**
   * This method is used for creating organisations type,
   *
   * @return Object of created orgType
   */
  public CompletionStage<Result> createOrgType() {
    startTrace("createOrgType");
    CompletionStage<Result> response = handelRequest();
    endTrace("createOrgType");
    return response;
  }

  /**
   * This method is used for updating organisations type,
   *
   * @return response code for failure or success
   */
  public CompletionStage<Result> updateOrgType() {
    startTrace("updateOrgType");
    CompletionStage<Result> response = handelRequest();
    endTrace(" updateOrgType");

    return response;
  }

  /**
   * This method is used for listing organisations type,
   *
   * @return list of Object of orgType
   */
  public CompletionStage<Result> listOrgType() {
    startTrace("listOrgType");
    CompletionStage<Result> response = handelRequest();
    endTrace("listOrgType");
    return response;
  }
}
