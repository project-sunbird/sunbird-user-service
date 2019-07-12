package org.sunbird.user.dao;

import org.sunbird.response.Response;

import java.util.Map;

public interface IOSDao {

    Response addEntity(Map<String,Object> entity) throws Exception;

    Response readEntity(Map<String,Object> entity, boolean requireLDResponse) throws Exception;

    Response readEntity(Map<String,Object> entity) throws Exception;

    Response searchEntity(Map<String,Object> entity) throws Exception;

    Response updateEntity(Map<String,Object> entity, String userId) throws Exception;

}
