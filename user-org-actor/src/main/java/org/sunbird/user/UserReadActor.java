package org.sunbird.user;

import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

/**
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"readUserById"},
        dispatcher = "search-read-dispatcher",
        asyncTasks = {}

)
public class UserReadActor extends BaseActor {

    @Override
    public void onReceive(Request request) throws Throwable {
        if(request.getOperation().equalsIgnoreCase("readUserById")){
            Response response = new Response();
            response.put("userId", "123-456-7890");
            response.put("firstName", "Amit");
            response.put("lastName", "kumar");
            sender().tell(response,self());
        } else {
            onReceiveUnsupportedMessage("UserCreateActor");
        }
    }
}
