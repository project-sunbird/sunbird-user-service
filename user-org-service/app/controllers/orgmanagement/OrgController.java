package controllers.orgmanagement;

import controllers.BaseController;
import java.util.concurrent.CompletionStage;

import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * This Controller is dedicated to organisations for create , update , search. CompletionStage: A
 * stage of a possibly asynchronous computation, that performs an action or computes a value when
 * another CompletionStage completes
 */
public class OrgController extends BaseController {

  /**
   * This method is used for creating organisation,
   *
   * @return Object of created orgType
   */
  public CompletionStage<Result> createOrg() {
    ProjectLogger.log(
            "OrgController :createOrg : request reached to org controller with request "+ request().body(), LoggerEnum.INFO.name());
    return handelRequest();
  }

  /**
   * This method is used for updating organisation,
   *
   * @return Response code for success if update complete else failure.
   */
  public CompletionStage<Result> updateOrg() {
    ProjectLogger.log(
            "OrgController : updateOrg : request reached to org controller to update orgwith request "+ request().body(), LoggerEnum.INFO.name());

    return handelRequest();
  }

  /**
   * This method is used for updating organisation status ,
   *
   * @return Response Code for success if update complete else failure
   */
  public CompletionStage<Result> updateOrgStatus() {

    ProjectLogger.log(
            "OrgController :updateOrgStatus : request reached to org controller with request "+ request().body(), LoggerEnum.INFO.name());
    return handelRequest();
  }

  /**
   * This method is used for getting organisation details ,
   *
   * @return Map of details related to organisation
   */
  public CompletionStage<Result> getOrgDetails() {
    ProjectLogger.log(
            "OrgController :getOrgDetails : request reached to org controllerwith request "+ request().body(), LoggerEnum.INFO.name());
    return handelRequest();
  }

  /**
   * This method is used for searching organisation ,
   *
   * @return Map of details related to organisation
   */
  public CompletionStage<Result> search() {
    ProjectLogger.log(
            "OrgController :search: request reached to org controllerwith request "+ request().body(), LoggerEnum.INFO.name());

    return handelRequest();
  }
}
