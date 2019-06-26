package org.sunbird.user;

import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actor.core.BaseActor;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.service.IUserService;
import org.sunbird.service.UserServiceImpl;

/**
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"createUser"},
        dispatcher = "user-dispatcher",
        asyncTasks = {}

)
public class UserCreateActor  extends BaseActor {

    private IUserService userService = null;
    private Response response = null;

    @Override
    public void onReceive(Request request) throws Throwable {
        if(request.getOperation().equalsIgnoreCase("createUser")){
            createUser(request);
        } else {
            onReceiveUnsupportedMessage("UserCreateActor");
        }
    }

    private void createUser(Request request) {
        userService = new UserServiceImpl();
        response = userService.createUser(request);
        sender().tell(response,self());
    }

}
