package utils;

import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.sunbird.util.ProjectLogger;
import play.api.Environment;
import play.api.inject.ApplicationLifecycle;

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
   * @param lifecycle ApplicationLifecycle
   * @param environment Environment
   */
  @Inject
  public ApplicationStart(ApplicationLifecycle lifecycle, Environment environment) {
    // Shut-down hook
    ProjectLogger.setLogLevel(); // Will set the value of log level from system env.
    lifecycle.addStopHook(
        () -> {
          return CompletableFuture.completedFuture(null);
        });
  }
}
