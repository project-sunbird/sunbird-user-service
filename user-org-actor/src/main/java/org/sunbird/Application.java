package org.sunbird;

import akka.actor.ActorRef;
import io.opensaber.registry.app.OpenSaberApplication;
import org.springframework.context.ApplicationContext;
import org.sunbird.actor.core.ActorCache;
import org.sunbird.actor.core.ActorService;
import org.sunbird.exception.message.Localizer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  Amit Kumar
 */
public class Application {

    private static Application instance = null;
    public static ApplicationContext applicationContext;
    private Localizer localizer = Localizer.getInstance();

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

        OpenSaberApplication.main(new String[0]);
        applicationContext = OpenSaberApplication.getContext();
    }

    public ActorRef getActorRef(String operation) {
        return ActorCache.getActorRef(operation);
    }
}
