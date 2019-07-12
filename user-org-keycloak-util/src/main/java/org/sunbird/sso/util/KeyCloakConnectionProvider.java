package org.sunbird.sso.util;

import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.PropertiesCache;

public class KeyCloakConnectionProvider {

  private static Keycloak keycloak;
  private static KeyCloakConnectionProvider cloakConnectionProvider;
  private static PropertiesCache cache = PropertiesCache.getInstance();
  public static final String SSO_URL = cache.readProperty(SSOConstant.SUNBIRD_SSO_URL);
  public static final String SSO_REALM = cache.readProperty(SSOConstant.SUNBIRD_SSO_RELAM);
  public static final String SSO_CLIENT_ID = cache.readProperty(SSOConstant.SUNBIRD_SSO_CLIENT_ID);
  private static final String SSO_USER_NAME = cache.readProperty(SSOConstant.SUNBIRD_SSO_USERNAME);
  private static final String SSO_PASSWORD = cache.readProperty(SSOConstant.SUNBIRD_SSO_PASSWORD);
  private static final int POOL_SIZE = 10;
  private static final String CLIENT_SECRET =
      cache.readProperty(SSOConstant.SUNBIRD_SSO_CLIENT_SECRET);

  private KeyCloakConnectionProvider() {
    try {
      validate();
      keycloak = initialiseConnection();
    } catch (Exception e) {
      ProjectLogger.log(
          "KeyCloakConnectionProvider:default constructor call fails" + e, LoggerEnum.ERROR.name());
    }
    registerShutDownHook();
  }

  private boolean validate() {
    if (StringUtils.isBlank(SSO_URL)
        || StringUtils.isBlank(SSO_PASSWORD)
        || StringUtils.isBlank(SSO_USER_NAME)
        || StringUtils.isBlank(SSO_CLIENT_ID)
        || StringUtils.isBlank(SSO_REALM)) {
      ProjectLogger.log(
          "KeyCloakConnectionProvider:validate all necessary values are not provided to established connection",
          LoggerEnum.INFO.name());
      System.exit(1);
    }
    return true;
  }

  private static KeyCloakConnectionProvider getInstance() {
    if (cloakConnectionProvider == null) {
      synchronized (cache) {
        if (cloakConnectionProvider != null) return cloakConnectionProvider;
        else cloakConnectionProvider = new KeyCloakConnectionProvider();
      }
    }
    return cloakConnectionProvider;
  }

  /**
   * Method to initializate the Keycloak connection
   *
   * @return Keycloak connection
   */
  private Keycloak initialiseConnection() throws Exception {
    KeycloakBuilder keycloakBuilder =
        KeycloakBuilder.builder()
            .serverUrl(SSO_URL)
            .realm(SSO_REALM)
            .username(SSO_USER_NAME)
            .password(SSO_PASSWORD)
            .clientId(SSO_CLIENT_ID)
            .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(POOL_SIZE).build());
    if (StringUtils.isNotBlank(CLIENT_SECRET)) {
      keycloakBuilder.clientSecret(CLIENT_SECRET);
    }
    keycloak = keycloakBuilder.build();

    ProjectLogger.log(
        "KeyCloakConnectionProvider:initialiseConnection key cloak instance is created successfully.",
        LoggerEnum.INFO.name());
    return keycloak;
  }

  /**
   * This method will provide key cloak connection instance.
   *
   * @return Keycloak
   */
  public static Keycloak getConnection() {
    getInstance();
    return keycloak;
  }

  /**
   * This class will be called by registerShutDownHook to register the call inside jvm , when jvm
   * terminate it will call the run method to clean up thecloakConnectionProvider resource.
   *
   * @author Manzarul
   */
  static class ResourceCleanUp extends Thread {
    public void run() {
      ProjectLogger.log(
          "KeyCloakConnectionProvider:ResourceCleanUp started resource cleanup.",
          LoggerEnum.INFO.name());
      keycloak.close();
      ProjectLogger.log(
          "KeyCloakConnectionProvider:ResourceCleanUp completed resource cleanup.",
          LoggerEnum.INFO.name());
    }
  }

  /** Register the hook for resource clean up. this will be called when jvm shut down. */
  public static void registerShutDownHook() {
    Runtime runtime = Runtime.getRuntime();
    runtime.addShutdownHook(new ResourceCleanUp());
    ProjectLogger.log(
        "KeyCloakConnectionProvider:registerShutDownHook ShutDownHook registered.",
        LoggerEnum.INFO.name());
  }
}
