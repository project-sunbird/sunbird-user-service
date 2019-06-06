package controllers.orgmanagement;

import java.util.concurrent.CompletionStage;

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
    return handelRequest();
  }
  
  /** 
   * This method is used for updating organisation, 
   * @return Response code for success if update complete else failure.
   */
  public CompletionStage<Result> updateOrg() {
    return handelRequest();
  }

  /** 
   * This method is used for updating organisation status , 
   * @return Response Code for success if update complete else failure 
   */
  public CompletionStage<Result> updateOrgStatus() {
    return handelRequest();
  }
  
  /** 
   * This method is used for getting organisation details  , 
   * @return Map of details related to organisation 
   */
  public CompletionStage<Result> getOrgDetails() {
    return handelRequest();
  }

  /** 
   * This method is used for searching organisation  , 
   * @return Map of details related to organisation 
   */
  public CompletionStage<Result> search() {
    return handelRequest();
  }

}
