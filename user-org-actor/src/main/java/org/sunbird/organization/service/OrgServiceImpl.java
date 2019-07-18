package org.sunbird.organization.service;

import org.sunbird.DaoImplType;
import org.sunbird.dto.SearchDTO;
import org.sunbird.dto.SearchDtoMapper;
import org.sunbird.organization.dao.IOrgDao;
import org.sunbird.organization.dao.OrgDaoFactory;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

public class OrgServiceImpl implements IOrgService {

  @Override
  public Response search(Request request) {
    SearchDtoMapper searchDTOMapper = SearchDtoMapper.getInstance();
    IOrgDao esDaoObject = OrgDaoFactory.getDaoImpl(DaoImplType.ES.getType());
    SearchDTO searchDto = searchDTOMapper.map(request.getRequest());
    return esDaoObject.search(searchDto);
  }
}
