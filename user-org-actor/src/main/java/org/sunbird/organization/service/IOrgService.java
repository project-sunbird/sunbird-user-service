package org.sunbird.organization.service;

import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

public interface IOrgService {

  /**
   * this method will be used to search org from elastic search
   *
   * @param request
   * @return response
   * @throws BaseException
   */
  Response search(Request request);
}
