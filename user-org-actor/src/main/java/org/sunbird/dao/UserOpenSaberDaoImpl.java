package org.sunbird.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sunbird.response.Response;
import io.opensaber.registry.helper.RegistryHelper;
import io.opensaber.registry.middleware.MiddlewareHaltException;
import org.sunbird.Application;
import org.sunbird.util.ProjectLogger;

import java.util.Map;

/**
 * @author Amit Kumar
 * This class will contains method to interact with open saber
 */
public class UserOpenSaberDaoImpl implements IUserDao {

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
                Map<String, Object> osResult = (Map<String, Object>) ((Map<String, Object>) osResponse.getResult()).get("User");
                String osId = (String) osResult.get("osid");
                ProjectLogger.log("Total time taken by opensaber for userId:: "+osId+", is = "+(System.currentTimeMillis() - startTime));
                response.put("userId", osId);
                return response;
            }
        } catch (MiddlewareHaltException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
