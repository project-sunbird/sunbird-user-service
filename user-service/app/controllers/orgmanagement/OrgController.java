package controllers.orgmanagement;

import java.util.concurrent.CompletionStage;

import org.sunbird.common.models.util.LoggerEnum;
import org.sunbird.common.models.util.ProjectLogger;

import controllers.BaseController;
import play.mvc.Result;

/**
 * This Controller is dedicated to organisations for create , update , search.
 *  CompletionStage: A stage of a possibly asynchronous
 * computation, that performs an action or computes a value when another
 * CompletionStage completes
 *
 */
public class OrgController extends BaseController {

  /** 
   * This method is used for creating organisation, 
   * @return Object of created orgType 
   */
  public CompletionStage<Result> createOrg() {
    ProjectLogger.log("Start Org Creation Contoller", LoggerEnum.INFO);
    return handelRequest();
  }
  
  /** 
   * This method is used for updating organisation, 
   * @return Response code for success if update complete else failure.
   */
  public CompletionStage<Result> updateOrg() {
    ProjectLogger.log("Start Org update Contoller", LoggerEnum.INFO);
    return handelRequest();
  }

  /** 
   * This method is used for updating organisation status , 
   * @return Response Code for success if update complete else failure 
   */
  public CompletionStage<Result> updateOrgStatus() {
    ProjectLogger.log("Start Org status update Contoller", LoggerEnum.INFO);
    return handelRequest();
  }
  
  /** 
   * This method is used for getting organisation details  , 
   * @return Map of details related to organisation 
   */
  public CompletionStage<Result> getOrgDetails() {
    ProjectLogger.log("Start Org details Contoller", LoggerEnum.INFO);
    return handelRequest();
  }

  /** 
   * This method is used for searching organisation  , 
   * @return Map of details related to organisation 
   */
  public CompletionStage<Result> search() {
    ProjectLogger.log("Start Org search Contoller", LoggerEnum.INFO);
    return handelRequest();
  }

}
