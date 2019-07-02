package org.sunbird.user.dao;

import org.sunbird.exception.BaseException;
import org.sunbird.response.Response;

import java.util.Map;

/**
 * @author anmolgupta
 */
public interface IUserESDao {

    /**
     * this method will be used to get user by id from elastic search.
     * @param userId
     * @return
     * @throws BaseException
     */
    public Response getUserById(String userId) throws BaseException;


    /**
     * this method will be used to search user from elasticsearch
     * @param request
     * @return
     * @throws BaseException
     */
    public Response searchUser(Map<String, Object> request) throws BaseException;
}
