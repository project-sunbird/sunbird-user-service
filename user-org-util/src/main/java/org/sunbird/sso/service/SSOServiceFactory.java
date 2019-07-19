package org.sunbird.sso.service;

import org.sunbird.sso.service.impl.KeyCloakServiceImpl;

/** @author Amit Kumar */
public class SSOServiceFactory {
  private static ISSOService ssoService = null;

  private SSOServiceFactory() {}

  /**
   * On call of this method , it will provide a new KeyCloakServiceImpl instance on each call.
   *
   * @return SSOManager
   */
  public static ISSOService getInstance() {
    if (null == ssoService) {
      ssoService = new KeyCloakServiceImpl();
    }
    return ssoService;
  }
}
