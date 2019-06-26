package org.sunbird.dao;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.opensaber.pojos.Response;
import io.opensaber.registry.middleware.MiddlewareHaltException;

public interface IUserDao {

    public Response addUser(Map<String, Object> user) throws MiddlewareHaltException, JsonProcessingException;
}
