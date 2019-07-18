package org.sunbird.organization.dao;

import org.sunbird.DaoImplType;

public class OrgDaoFactory {

  private OrgDaoFactory() {}

  /**
   * this method will return the object of the implementation class on the basis of the provided dao
   * type.
   *
   * @param daoImplType
   * @return object
   */
  public static IOrgDao getDaoImpl(String daoImplType) {
    if (daoImplType.equalsIgnoreCase(DaoImplType.ES.getType())) {
      return OrgEsDaoImpl.getInstance();
    }
    return null;
  }
}
