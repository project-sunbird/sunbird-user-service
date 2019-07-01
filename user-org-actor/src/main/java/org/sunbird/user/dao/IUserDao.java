package org.sunbird.user.dao;

import org.sunbird.exception.BaseException;
import org.sunbird.exception.ProjectCommonException;
import org.sunbird.exception.message.Localizer;
import org.sunbird.response.Response;

import java.util.Map;


/**
 * @author Amit Kumar
 */
public interface IUserDao {
    Localizer localizer = Localizer.getInstance();

    public Response addUser(Map<String, Object> user) throws BaseException;
}
