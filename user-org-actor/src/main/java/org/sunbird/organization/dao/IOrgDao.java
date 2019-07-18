package org.sunbird.organization.dao;

import org.sunbird.dto.SearchDTO;
import org.sunbird.exception.BaseException;
import org.sunbird.response.Response;

public interface IOrgDao {

  /**
   * this method will be used to search org from elastic search
   *
   * @param searchDTO
   * @return response
   * @throws BaseException
   */
  Response search(SearchDTO searchDTO);
}
