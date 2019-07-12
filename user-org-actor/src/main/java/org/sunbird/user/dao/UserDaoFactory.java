package org.sunbird.user.dao;

import org.sunbird.DaoImplType;

/**
 * this class is used to get object for daoImpl on the basis of dao type
 * @author Amit Kumar
 */
public class UserDaoFactory {

    // private constructor restricted to this class itself
    private UserDaoFactory() {
    }

    /**
     * this method will return the object of the implementation class on the basis of the provided dao type.
     * @param daoImplType
     * @return object
     */
    public static Object getDaoImpl(String daoImplType) {

        if (daoImplType.equalsIgnoreCase(DaoImplType.USER_OS.getType())) {
            return UserOSDaoImpl.getInstance();
        } else if (daoImplType.equalsIgnoreCase(DaoImplType.USER_ADDRESS.getType())) {
            return UserOSAddressDaoImpl.getInstance();
        } else if (daoImplType.equalsIgnoreCase(DaoImplType.ES.getType())) {
            return UserESDaoImpl.getInstance();
        }

        return null;
    }
}
