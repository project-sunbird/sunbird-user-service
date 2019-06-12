package controllers.orgmanagement;

import controllers.BaseController;
import java.util.concurrent.CompletionStage;
import play.mvc.Result;

/**
 * This Controller is dedicated to organisations for create , update , search. CompletionStage: A
 * stage of a possibly asynchronous computation, that performs an action or computes a value when
 * another CompletionStage completes
 */
public class OrgController extends BaseController {

  public static final String TAG = OrgController.class.getName();

  /**
   * This method is used for creating organisation,
   *
   * @return Object of created orgType
   */
  public CompletionStage<Result> createOrg() {
    printProjectLogs(OrgController.class.getName(), "createOrg", true);
    CompletionStage<Result> result = handelRequest();
    printProjectLogs(TAG, "createOrg", false);
    return result;
  }

  /**
   * This method is used for updating organisation,
   *
   * @return Response code for success if update complete else failure.
   */
  public CompletionStage<Result> updateOrg() {
    printProjectLogs(OrgController.class.getName(), "updateOrg", true);
    CompletionStage<Result> response = handelRequest();
    printProjectLogs(OrgController.class.getName(), "updateOrg", false);
    return response;
  }

  /**
   * This method is used for updating organisation status ,
   *
   * @return Response Code for success if update complete else failure
   */
  public CompletionStage<Result> updateOrgStatus() {

    printProjectLogs(OrgController.class.getName(), "updateOrgStatus", true);
    CompletionStage<Result> response = handelRequest();
    printProjectLogs(OrgController.class.getName(), "updateOrgStatus", false);
    return response;
  }

  /**
   * This method is used for getting organisation details ,
   *
   * @return Map of details related to organisation
   */
  public CompletionStage<Result> getOrgDetails() {
    printProjectLogs(OrgController.class.getName(), "getOrgDetails", true);
    CompletionStage<Result> response = handelRequest();
    printProjectLogs(OrgController.class.getName(), "getOrgDetails", false);
    return response;
  }

  /**
   * This method is used for searching organisation ,
   *
   * @return Map of details related to organisation
   */
  public CompletionStage<Result> search() {
    printProjectLogs(OrgController.class.getName(), "search", true);
    CompletionStage<Result> response = handelRequest();
    printProjectLogs(OrgController.class.getName(), "search", false);
    return response;
  }
}
