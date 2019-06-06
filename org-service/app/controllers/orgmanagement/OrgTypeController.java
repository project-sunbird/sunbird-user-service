package controllers.orgmanagement;

import java.util.concurrent.CompletionStage;

import org.sunbird.common.models.util.LoggerEnum;
import org.sunbird.common.models.util.ProjectLogger;

import controllers.BaseController;
import play.mvc.Result;

/**
 * This Controller is dedicated to organisation type creation , update , listing .
 * CompletionStage: A stage of a possibly asynchronous computation,
 *  that performs an action or computes a value when another CompletionStage completes
 *
 */
public class OrgTypeController extends BaseController {

  /** 
   * This method is used for creating organisations type, 
   * @return Object of created orgType
   */
  public CompletionStage<Result> createOrgType() {
    ProjectLogger.log("Start Org type Creation Contoller",LoggerEnum.INFO);
    return handelRequest();
  }
  /** 
   * This method is used for updating organisations type, 
   * @return response code for failure or success
   */
  public CompletionStage<Result> updateOrgType() {
    ProjectLogger.log("Start Org update Contoller",LoggerEnum.INFO);
    return handelRequest();
  }

  /** 
   * This method is used for listing organisations type, 
   * @return list of Object of orgType
   */
  public CompletionStage<Result> listOrgType() {
    ProjectLogger.log("Start Org list Contoller",LoggerEnum.INFO);
    return handelRequest();
  }
}
