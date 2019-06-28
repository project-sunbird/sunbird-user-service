package org.sunbird.user.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.opensaber.registry.helper.RegistryHelper;
import io.opensaber.registry.middleware.MiddlewareHaltException;
import org.apache.commons.lang3.StringUtils;
import org.apache.tinkerpop.gremlin.driver.message.ResponseMessage;
import org.sunbird.Application;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.ProjectCommonException;
import org.sunbird.exception.UserException;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.Localizer;
import org.sunbird.exception.message.ResponseCode;
import org.sunbird.response.Response;
import org.sunbird.user.UserJsonKey;
import org.sunbird.util.ProjectLogger;

import java.util.Map;

/**
 * @author Amit Kumar
 * This class will contains method to interact with open saber
 */
public class UserOpenSaberDaoImpl implements IUserDao {

    private Localizer localizer = Localizer.getInstance();
    private RegistryHelper registryHelper;
    private ObjectMapper objectMapper;

    public UserOpenSaberDaoImpl() {
        objectMapper = new ObjectMapper();
        registryHelper = Application.applicationContext.getBean(RegistryHelper.class);
    }

    @Override
    public Response addUser(Map<String, Object> user) {
        Response response = new Response();
        try {
            long startTime = System.currentTimeMillis();
            io.opensaber.pojos.Response osResponse = registryHelper.addEntity(objectMapper.convertValue(user, JsonNode.class));
            if (null != osResponse.getResult()) {
                Map<String, Object> osResult = (Map<String, Object>) ((Map<String, Object>) osResponse.getResult()).get(StringUtils.capitalize(UserJsonKey.USER));
                String osId = (String) osResult.get(UserJsonKey.OSID);
                ProjectLogger.log("Total time taken by opensaber for processing of userId:: "+osId+", is = "+(System.currentTimeMillis() - startTime));
                response.put(UserJsonKey.USER_ID, osId);
                return response;
            }
        } catch (Exception e) {
            ProjectLogger.log("Exception occurred while adding user to open saber.",e);
        }
        return null;
    }
}
