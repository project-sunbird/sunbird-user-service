package org.sunbird.user.dao;

import org.sunbird.exception.BaseException;
import org.sunbird.exception.ProjectCommonException;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.ResponseCode;
import org.sunbird.response.Response;
import org.sunbird.util.ProjectLogger;

import java.util.Map;

public class UserOSAddressDaoImpl implements IUserAddressDao {

    private UserOSAddressDaoImpl() {}

    /**
     * this method should be used to get the instance of the class
     * @return class object
     */
    public static UserOSAddressDaoImpl getInstance() {
        return new UserOSAddressDaoImpl();
    }

    @Override
    public Response insertAddress(Map<String, Object> userAddress) throws BaseException {
        try {
            return OSDaoImpl.getInstance().addEntity(userAddress);
        } catch (Exception e) {
            ProjectLogger.log("Exception occurred while adding user address to open saber.", e);
            throw new ProjectCommonException.ServerError(IResponseMessage.INTERNAL_ERROR, localizer.getMessage(IResponseMessage.INTERNAL_ERROR, null), ResponseCode.SERVER_ERROR.getCode());
        }
    }

    @Override
    public Response updateAddress(Map<String, Object> userAddress) {
        return null;
    }
}
