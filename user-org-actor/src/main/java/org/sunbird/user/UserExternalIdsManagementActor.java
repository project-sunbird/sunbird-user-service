package org.sunbird.user;

import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.request.Request;

/**
 * this actor class is used to process the user ExternalId entity
 *
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"insertUserExternalId", "updateUserExternalId"},
        dispatcher = "user-dispatcher",
        asyncTasks = {}
)
public class UserExternalIdsManagementActor extends BaseActor {
    @Override
    public void onReceive(Request request) throws Throwable {

    }
}
