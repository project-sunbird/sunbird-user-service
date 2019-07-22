package org.sunbird.user.service;

import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

public interface IAddressService {

  /**
   * This method will insert user address.
   *
   * @param request
   * @return Response
   */
  Response createAddress(Request request) throws BaseException;
}
