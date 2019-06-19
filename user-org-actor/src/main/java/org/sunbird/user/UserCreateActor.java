package org.sunbird.user;

import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actor.core.BaseActor;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;

/**
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"createUser"},
        dispatcher = "user-dispatcher",
        asyncTasks = {}

)
public class UserCreateActor  extends BaseActor {

    @Override
    public void onReceive(Request request) throws Throwable {
        ProjectLogger.log("UserCreateActor: onReceive called for operation: " + request.getOperation(), LoggerEnum.INFO);

        if(request.getOperation().equalsIgnoreCase("createUser")){
            Response response = new Response();
            response.put("userId", "123-456-7890");
            sender().tell(response,self());
        } else {
            onReceiveUnsupportedMessage("UserCreateActor");
        }
    }
}
