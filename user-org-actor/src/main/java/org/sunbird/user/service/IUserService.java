package org.sunbird.user.service;

import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

public interface IUserService {

    /**
     * This method will create user.
     * @param request
     * @return Response
     */
    Response createUser(Request request) throws BaseException;

    /**
     * This method will read user.
     * @param userId
     * @return Response
     */
    Response readUser(String userId) throws BaseException;
}
