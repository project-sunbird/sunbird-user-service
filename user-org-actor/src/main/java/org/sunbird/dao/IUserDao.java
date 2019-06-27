package org.sunbird.dao;

import org.sunbird.response.Response;

import java.util.Map;


/**
 * @author Amit Kumar
 */
public interface IUserDao {

    public Response addUser(Map<String, Object> user);
}
