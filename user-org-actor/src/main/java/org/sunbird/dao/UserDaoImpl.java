package org.sunbird.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.opensaber.pojos.Response;
import io.opensaber.registry.helper.RegistryHelper;
import io.opensaber.registry.middleware.MiddlewareHaltException;
import org.sunbird.Application;

import java.util.Map;

public class UserDaoImpl implements IUserDao {

    private RegistryHelper registryHelper;
    private ObjectMapper objectMapper;

    public UserDaoImpl() {
        objectMapper = new ObjectMapper();
        registryHelper = Application.applicationContext.getBean(RegistryHelper.class);
    }

    @Override
    public Response addUser(Map<String, Object> user) throws MiddlewareHaltException, JsonProcessingException {
        return registryHelper.addEntity(objectMapper.convertValue(user, JsonNode.class));
    }
}
