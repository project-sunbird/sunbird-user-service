package org.sunbird.user.dao;

import org.sunbird.exception.BaseException;
import org.sunbird.exception.ProjectCommonException;
import org.sunbird.exception.message.Localizer;
import org.sunbird.response.Response;

import java.util.Map;


/**
 * @author Amit Kumar
 */
public interface IUserOSDao {
    Localizer localizer = Localizer.getInstance();


    /**
     * this method will create a user in open saber
     * @param user
     * @return response
     * @throws BaseException
     */
    public Response createUser(Map<String, Object> user) throws BaseException;


}
