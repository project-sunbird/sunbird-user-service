package org.sunbird.user.dao;

import java.util.Map;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.message.Localizer;
import org.sunbird.response.Response;

/** @author Amit Kumar */
public interface IUserAddressDao {

  Localizer localizer = Localizer.getInstance();
  /**
   * @param userAddress
   * @return Response
   */
  Response createAddress(Map<String, Object> userAddress) throws BaseException;

  /**
   * @param userAddress
   * @return Response
   */
  Response updateAddress(Map<String, Object> userAddress) throws BaseException;
}
