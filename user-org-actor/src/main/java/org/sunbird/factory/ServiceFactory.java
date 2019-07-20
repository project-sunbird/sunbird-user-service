package org.sunbird.factory;

import org.sunbird.ServiceImplType;
import org.sunbird.user.service.UserServiceImpl;

/**
 * This class is created for keeping all service's object creation.
 */
public class ServiceFactory  {

    public static Object getService(String serviceName) {
        Object serviceObj = null;
        if(serviceName.equals(ServiceImplType.USER.getServiceType())) {
            serviceObj = UserServiceImpl.getInstance();
        }
        return serviceObj;
    }
}
