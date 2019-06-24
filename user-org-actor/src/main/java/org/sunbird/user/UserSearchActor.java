package org.sunbird.user;


import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actor.core.BaseActor;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

/**
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"searchUser"},
        dispatcher = "search-read-dispatcher",
        asyncTasks = {}

)
public class UserSearchActor extends BaseActor {

    @Override
    public void onReceive(Request request) throws Throwable {
        if(request.getOperation().equalsIgnoreCase("searchUser")){
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
