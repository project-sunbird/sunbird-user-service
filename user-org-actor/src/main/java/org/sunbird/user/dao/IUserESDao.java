package org.sunbird.user.dao;

import org.sunbird.exception.BaseException;
import org.sunbird.response.Response;

import java.util.Map;

/**
 * @author anmolgupta
 */
public interface IUserESDao {
    public Response getUserById(String userId) throws BaseException;

    public Response searchUser(Map<String, Object> request) throws BaseException;
}
