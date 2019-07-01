package org.sunbird;

import akka.actor.ActorRef;
import org.sunbird.actor.core.ActorCache;
import org.sunbird.actor.core.ActorService;
import org.sunbird.exception.ActorServiceException;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.Localizer;
import org.sunbird.exception.message.ResponseCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amit Kumar
 */
public class Application {

    // static variable instance of type ActorService
    private static Application instance = null;
    private Localizer localizer = Localizer.getInstance();

    // private constructor restricted to this class itself
    private Application() {
    }

    // static method to create instance of ActorService class
    public static Application getInstance() {
        if (instance == null)
            instance = new Application();

        return instance;
    }

    // instantiate actor system and actors
    public void init() {
        List<String> actorClassPaths = new ArrayList<>();
        actorClassPaths.add("org.sunbird");
        ActorService.getInstance().init("userOrgActorSystem", actorClassPaths);
    }

    public ActorRef getActorRef(String operation) throws BaseException {
        return ActorCache.getActorRef(operation);

    }
}
