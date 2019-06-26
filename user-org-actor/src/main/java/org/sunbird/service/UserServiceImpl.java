package org.sunbird.service;

import org.sunbird.dao.IUserDao;
import org.sunbird.dao.UserDaoImpl;
import org.sunbird.request.Request;
import org.sunbird.response.Response;

import java.util.Map;

/**
 * @author Amit Kumar
 * This class will contains user entity related CRUD method
 */
public class UserServiceImpl implements IUserService{

    private IUserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public Response createUser(Request request) {
        Response response = new Response();
        try {
            io.opensaber.pojos.Response osResponse = userDao.addUser(request.getRequest());
            if(null != osResponse.getResult()) {
                Map<String, Object> osResult = (Map<String, Object>) ((Map<String, Object>) osResponse.getResult()).get("User");
                String osId = (String) osResult.get("osid");
                response.put("userId", osId);
            } else {
                //throw exception as user not created
            }
        }catch (Exception ex){
            // catch the exception and throw BaseException
        }
        return response;
    }
}
