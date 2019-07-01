package org.sunbird.user.dao;

import org.sunbird.DaoImplType;

/**
 * @author Amit Kumar
 */
public class UserDaoFactory {

    private static UserDaoFactory instance;
    // private constructor restricted to this class itself
    private UserDaoFactory() { }

    // static method to create instance of ActorService class
    public static UserDaoFactory getInstance()
    {
        if (instance == null)
            instance = new UserDaoFactory();

        return instance;
    }



    private IUserOSDao userDao;

    public Object getDaoImpl(String daoImplType){

        if(daoImplType.equalsIgnoreCase(DaoImplType.OPEN_SABER.getType())) {
            return new UserOpenSaberOSDaoImpl();
        }
        else if(daoImplType.equalsIgnoreCase(DaoImplType.ES.getType())){
            return new UserEsOSDaoImpl();
        }


        return null;
    }
}
