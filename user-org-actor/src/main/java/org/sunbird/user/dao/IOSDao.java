package org.sunbird.user.dao;

import com.fasterxml.jackson.databind.JsonNode;
import org.sunbird.response.Response;

import java.util.Map;

/**
 * This class contains method to interact with open saber
 */
public interface IOSDao {

    /**
     * This method will add entity to open saber
     * @param entity
     * @return Response
     * @throws Exception
     */
    Response addEntity(Map<String,Object> entity) throws Exception;

    /**
     * This method will read data from open saber and return response in JsonLD
     * @param entity
     * @return Response
     * @throws Exception
     */
    Response readJSONLDEntity(Map<String,Object> entity) throws Exception;

    /**
     * This method will read data from open saber
     * @param entity
     * @return response
     * @throws Exception
     */
    Response readEntity(JsonNode entity) throws Exception;

    /**
     * This method will search entity from open saber
     * @param entity
     * @return response
     * @throws Exception
     */
    Response searchEntity(Map<String,Object> entity) throws Exception;

    /**
     * This method will update the existing entity in open saber.
     * @param entity
     * @param entityId
     * @return Response
     * @throws Exception
     */
    Response updateEntity(Map<String,Object> entity, String entityId) throws Exception;

}
