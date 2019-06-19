package org.sunbird;

import akka.actor.ActorRef;
import org.sunbird.actor.core.ActorCache;
import org.sunbird.actor.core.ActorService;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.ResponseCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  Amit Kumar
 */
public class Application {

    // static variable instance of type ActorService
    private static Application instance = null;

    // private constructor restricted to this class itself
    private Application() { }

    // static method to create instance of ActorService class
    public static Application getInstance()
    {
        if (instance == null)
            instance = new Application();

        return instance;
    }

    // instantiate actor system and actors
    public void init() {
        List<String> actorClassPaths = new ArrayList<>();
        actorClassPaths.add("org.sunbird");
        ActorService.getInstance().init("userOrgActorSystem",actorClassPaths);
    }

    public ActorRef getActorRef(String operation) throws BaseException{
        ActorRef actorRef = ActorCache.getActorRef(operation);
        if(null != actorRef){
            return actorRef;
        } else {
            BaseException.throwClientErrorException(ResponseCode.invalidOperationName);
        }
        return actorRef;
    }


}
