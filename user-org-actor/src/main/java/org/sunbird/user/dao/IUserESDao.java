package org.sunbird.user.dao;

import org.sunbird.dto.SearchDTO;
import org.sunbird.exception.BaseException;
import org.sunbird.response.Response;

/**
 * this is an interface class used to perform search operation from elastic search
 * @author anmolgupta
 */
public interface IUserESDao {

    /**
     * this method will be used to get user by id from elastic search.
     * @param userId
     * @return response
     * @throws BaseException
     */
    public Response getUserById(String userId) throws BaseException;


    /**
     * this method will be used to search user from elastic search
     * @param searchDTO
     * @return response
     * @throws BaseException
     */
    public Response searchUser(SearchDTO searchDTO) throws BaseException;
}
