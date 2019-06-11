package controllers.orgmanagement;

import controllers.BaseController;

import java.util.concurrent.CompletionStage;

import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
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
        ProjectLogger.log(
                "OrgTypeController :createOrgType: request reached to orgMember controller with request "+ request().body(), LoggerEnum.INFO.name());
        return handelRequest();
    }

    /**
     * This method is used for updating organisations type,
     *
     * @return response code for failure or success
     */
    public CompletionStage<Result> updateOrgType() {
        ProjectLogger.log(
                "OrgTypeController :createOrgType: request reached to orgMember controller with request + request().body()", LoggerEnum.INFO.name());
        return handelRequest();
    }

    /**
     * This method is used for listing organisations type,
     *
     * @return list of Object of orgType
     */
    public CompletionStage<Result> listOrgType() {
        ProjectLogger.log(
                "OrgTypeController :listOrgType: request reached to orgMember controller with request "+ request().body(), LoggerEnum.INFO.name());
        return handelRequest();
    }
}
