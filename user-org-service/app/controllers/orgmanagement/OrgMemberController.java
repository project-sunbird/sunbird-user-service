package controllers.orgmanagement;

import controllers.BaseController;
import java.util.concurrent.CompletionStage;
import play.mvc.Result;

/**
 * This Controller is dedicated to addition and removal of members from an organisation.
 * CompletionStage: A stage of a possibly asynchronous computation, that performs an action or
 * computes a value when another CompletionStage completes
 */
public class OrgMemberController extends BaseController {

  /**
   * This method is used for adding a member to organisation,
   *
   * @return Response code for failure if member is already part of org, cannot be added to org or
   *     success,
   */
  public CompletionStage<Result> addMemberToOrganisation() {
    startTrace("addMemberToOrganisation");
    CompletionStage<Result> response = handelRequest();
    endTrace("addMemberToOrganisation");
    return response;
  }

  /**
   * This method is used for removing a member from organisation,
   *
   * @return Response code for failure if member not belongs to org,
   */
  public CompletionStage<Result> removeMemberFromOrganisation() {

    startTrace("removeMemberFromOrganisation");
    CompletionStage<Result> response = handelRequest();
    endTrace("removeMemberFromOrganisation");
    return response;
  }
}
