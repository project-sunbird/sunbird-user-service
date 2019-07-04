package org.sunbird.user.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.opensaber.registry.helper.RegistryHelper;
import org.sunbird.Application;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.ProjectCommonException;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.Localizer;
import org.sunbird.exception.message.ResponseCode;
import org.sunbird.response.Response;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.jsonkey.JsonKey;

import java.util.Map;

/**
 * @author Amit Kumar
 * This class will contains method to interact with open saber
 */

public class UserOSDaoImpl implements IUserOSDao {

    private Localizer localizer = Localizer.getInstance();
    private RegistryHelper registryHelper;
    private ObjectMapper objectMapper;

    private UserOSDaoImpl() {
        objectMapper = new ObjectMapper();
        registryHelper = Application.applicationContext.getBean(RegistryHelper.class);
    }


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
        Response response = new Response();
        try {
            String userId = registryHelper.addEntity(objectMapper.convertValue(user, JsonNode.class), "");
            response.put(JsonKey.USER_ID, userId);
            return response;
        } catch (Exception e) {
            ProjectLogger.log("Exception occurred while adding user to open saber.", e);
            throw new ProjectCommonException.ServerError(IResponseMessage.INTERNAL_ERROR, localizer.getMessage(IResponseMessage.INTERNAL_ERROR, null), ResponseCode.SERVER_ERROR.getCode());
        }
    }
}
