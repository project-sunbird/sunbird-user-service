package org.sunbird.organization;


import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.request.Request;

/**
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"createOrg"},
        asyncTasks = {}
)
public class OrgCreateActor extends BaseActor {

    @Override
    public void onReceive(Request request) throws Throwable {
        if(request.getOperation().equalsIgnoreCase("createOrg")){
            System.out.println("Org created!!!");
        }

    }
}
