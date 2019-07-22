package org.sunbird.user.dao;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.opensaber.registry.model.DBConnectionInfoMgr;
import org.sunbird.Application;
import java.util.Map;
import org.sunbird.dto.SearchDTO;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.ProjectCommonException;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.ResponseCode;
import org.sunbird.response.Response;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;

/** @author Amit Kumar This class will contains method to interact with open saber */
public class UserOSDaoImpl implements IUserDao {
    private static UserOSDaoImpl instance = null;

    private UserOSDaoImpl() {
    }


    /**
     * this method should be used to get the instance of the class
     * @return class object
     */
    public static UserOSDaoImpl getInstance() throws ProjectCommonException.ServerError {
        if (null == instance) {
            instance = new UserOSDaoImpl();
        }
        return instance;
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
          throw new ProjectCommonException.ServerError(
              IResponseMessage.INTERNAL_ERROR,
              localizer.getMessage(IResponseMessage.INTERNAL_ERROR, null),
              ResponseCode.SERVER_ERROR.getCode());
        }
      }

    /**
     * this method is used to read user record from OS.
     * @param userId
     * @return response
     * @throws BaseException
     */
    @Override
    public Response getUserById(String userId) throws BaseException {
        try {
            ObjectNode idNode = JsonNodeFactory.instance.objectNode();
            ObjectNode userNode =  JsonNodeFactory.instance.objectNode();
            DBConnectionInfoMgr dBConnectionInfoMgr = Application.applicationContext.getBean(DBConnectionInfoMgr.class);
            idNode.put(dBConnectionInfoMgr.getUuidPropertyName(), userId);
            userNode.set("User", idNode);
            return OSDaoImpl.getInstance().readEntity(userNode);
        } catch (Exception e) {
            ProjectLogger.log(
                    "UserOSDaoImpl:readUser: "
                            + "Exception in getting the record from opensaber : "
                            + e.getMessage(),
                    LoggerEnum.ERROR.name());
            throw new ProjectCommonException.ServerError(IResponseMessage.INTERNAL_ERROR, localizer.getMessage(IResponseMessage.INTERNAL_ERROR, null), ResponseCode.SERVER_ERROR.getCode());
        }
    }

    @Override
    public Response searchUser(SearchDTO searchDTO) throws BaseException {
        return null;
    }
}
