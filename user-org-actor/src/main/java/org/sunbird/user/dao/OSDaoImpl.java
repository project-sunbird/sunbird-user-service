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

public class OSDaoImpl implements IOSDao{

    private Localizer localizer = Localizer.getInstance();
    private RegistryHelper registryHelper;
    private ObjectMapper objectMapper;

    private OSDaoImpl() {
        objectMapper = new ObjectMapper();
        registryHelper = Application.applicationContext.getBean(RegistryHelper.class);
    }

    /**
     * this method should be used to get the instance of the class
     * @return class object
     */
    public static OSDaoImpl getInstance() {
        return new OSDaoImpl();
    }

    @Override
    public Response addEntity(Map<String, Object> entity) throws Exception {
        Response response = new Response();
        JsonNode userJsonNode = objectMapper.convertValue(entity, JsonNode.class);
        String entityId = registryHelper.addEntity(userJsonNode, "");
        Map<String,Object> entityMap = objectMapper.convertValue(userJsonNode, Map.class);
        entityMap.put(JsonKey.ID,entityId);
        response.putAll(entityMap);
        return response;
    }

    @Override
    public Response readEntity(Map<String, Object> entity, boolean requireLDResponse) throws Exception {
        return null;
    }

    @Override
    public Response readEntity(Map<String, Object> entity) throws Exception {
        return null;
    }

    @Override
    public Response searchEntity(Map<String, Object> entity) throws Exception {
        return null;
    }

    @Override
    public Response updateEntity(Map<String, Object> entity, String userId) throws Exception {
        return null;
    }
}
