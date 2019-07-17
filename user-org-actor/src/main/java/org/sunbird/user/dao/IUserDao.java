package org.sunbird.user.dao;

import org.sunbird.dto.SearchDTO;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.message.Localizer;
import org.sunbird.response.Response;

import java.util.Map;


/**
 * @author Amit Kumar
 */
public interface IUserDao {

    Localizer localizer = Localizer.getInstance();


    /**
     * this method will create a user in open saber
     * @param user
     * @return response
     * @throws BaseException
     */
    Response createUser(Map<String, Object> user) throws BaseException;

    /**
     * this method will be used to get user by id from elastic search.
     * @param userId
     * @return response
     * @throws BaseException
     */
    Response getUserById(String userId) throws BaseException;


    /**
     * this method will be used to search user from elastic search
     * @param searchDTO
     * @return response
     * @throws BaseException
     */
    Response searchUser(SearchDTO searchDTO) throws BaseException;

}
