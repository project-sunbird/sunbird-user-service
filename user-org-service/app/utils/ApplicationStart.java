package utils;


import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.sunbird.Application;

import play.api.Environment;
import play.api.inject.ApplicationLifecycle;
import utils.validator.schema.SchemaFactory;

/**
 * This class will be called after on application startup. only one instance of this class will be
 * created. StartModule class has responsibility to eager load this class.
 *
 * @author manzarul
 */
@Singleton
public class ApplicationStart {
    /**
     * All one time initialization which required during server startup will fall here.
     *
     * @param lifecycle   ApplicationLifecycle
     * @param environment Environment
     */
    @Inject
    public ApplicationStart(ApplicationLifecycle lifecycle, Environment environment) {
        SchemaFactory.getInstance().initSchemas();  // this method will load all the schemas and save into cache ,on startup of Application
        Application.getInstance().init();          //instantiate actor system and initialize all the actors
        // Shut-down hook
        lifecycle.addStopHook(
                () -> {
                    return CompletableFuture.completedFuture(null);
                });
    }
}
