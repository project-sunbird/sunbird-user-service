package org.sunbird.user.dao;

import org.sunbird.dto.SearchDTO;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.ProjectCommonException;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.ResponseCode;
import org.sunbird.response.Response;
import org.sunbird.util.ProjectLogger;

import java.util.Map;

/**
 * @author Amit Kumar
 * This class will contains method to interact with open saber
 */

public class UserOSDaoImpl implements IUserDao {

    private UserOSDaoImpl() {    }


    /**
     * this method should be used to get the instance of the class
     * @return class object
     */
    public static UserOSDaoImpl getInstance() {
        return new UserOSDaoImpl();
    }

    /**
     * this method is used to create a used in OS.
     * @param user
     * @return response
     * @throws BaseException
     */
    @Override
    public Response createUser(Map<String, Object> user) throws BaseException {
        try {
            return OSDaoImpl.getInstance().addEntity(user);
        } catch (Exception e) {
            ProjectLogger.log("Exception occurred while adding user to open saber.", e);
            throw new ProjectCommonException.ServerError(IResponseMessage.INTERNAL_ERROR, localizer.getMessage(IResponseMessage.INTERNAL_ERROR, null), ResponseCode.SERVER_ERROR.getCode());
        }
    }


    @Override
    public Response getUserById(String userId) throws BaseException {
        return null;
    }

    @Override
    public Response searchUser(SearchDTO searchDTO) throws BaseException {
        return null;
    }
}
