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
        printProjectLogs(OrgTypeController.class.getSimpleName(), "createOrgType", true);
        CompletionStage<Result> response = handelRequest();
        printProjectLogs(OrgTypeController.class.getSimpleName(), "createOrgType", false);
        return response;
    }

    /**
     * This method is used for updating organisations type,
     *
     * @return response code for failure or success
     */
    public CompletionStage<Result> updateOrgType() {
        printProjectLogs(OrgTypeController.class.getSimpleName(), "updateOrgType", true);
        CompletionStage<Result> response = handelRequest();
        printProjectLogs(OrgTypeController.class.getSimpleName(), "updateOrgType", false);

        return response;
    }

    /**
     * This method is used for listing organisations type,
     *
     * @return list of Object of orgType
     */
    public CompletionStage<Result> listOrgType() {
        printProjectLogs(OrgTypeController.class.getSimpleName(),"listOrgType",true);
        CompletionStage<Result> response = handelRequest();
        printProjectLogs(OrgTypeController.class.getSimpleName(),"listOrgType",false);
        return response;
    }
}
