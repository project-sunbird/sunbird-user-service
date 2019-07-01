package org.sunbird.user.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.opensaber.registry.helper.RegistryHelper;
import org.apache.commons.lang3.StringUtils;
import org.sunbird.Application;
import org.sunbird.exception.message.Localizer;
import org.sunbird.response.Response;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.jsonkey.JsonKey;

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
            String userId = registryHelper.addEntity(objectMapper.convertValue(user, JsonNode.class),"");
            if (StringUtils.isNotBlank(userId)) {
                ProjectLogger.log("Total time taken by opensaber for processing of userId:: "+userId+", is = "+(System.currentTimeMillis() - startTime));
                response.put(JsonKey.USER_ID, userId);
                return response;
            }
        } catch (Exception e) {
            ProjectLogger.log("Exception occurred while adding user to open saber.",e);
        }
        return null;
    }
}
