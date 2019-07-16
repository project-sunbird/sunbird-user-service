package org.sunbird.user.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.sunbird.DaoImplType;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.user.dao.IUserAddressDao;
import org.sunbird.user.dao.UserDaoFactory;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.jsonkey.JsonKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements IAddressService {
    @Override
    public Response createAddress(Request request) throws BaseException {
        return create(request);
    }

    private Response create(Request request) {
        Map<String, Object> requestMap = request.getRequest();
        String userId = (String)requestMap.get(JsonKey.USER_ID);
        List<Map<String, Object>> addressList =
                (List<Map<String, Object>>) requestMap.get(JsonKey.ADDRESS);
        Response response = new Response();
        List<String> errMsgs = new ArrayList<>();
        List<Map<String, Object>> responseAddressList = new ArrayList<>();
        try {
            for (int i = 0; i < addressList.size(); i++) {
                try {
                    Map<String, Object> address = addressList.get(i);
                    responseAddressList.add(createAddress(userId, address));
                } catch (BaseException e) {
                    errMsgs.add(e.getMessage());
                    ProjectLogger.log(
                            "AddressServiceImpl:insert Address: Exception occurred with error message = "
                                    + e.getMessage(),
                            e);
                } catch (Exception e) {
                    errMsgs.add("Error occurred while inserting address details.");
                    ProjectLogger.log(
                            "AddressServiceImpl:insert Address: Generic exception occurred with error message = "
                                    + e.getMessage(),
                            e);
                }
            }
        } catch (Exception e) {
            errMsgs.add(e.getMessage());
            ProjectLogger.log(e.getMessage(), e);
        }
        response.put(JsonKey.ADDRESS, responseAddressList);
        response.put(JsonKey.KEY, JsonKey.ADDRESS);
        if (CollectionUtils.isNotEmpty(errMsgs)) {
            response.put(JsonKey.ERROR_MSG, errMsgs);
        } else {
            response.put(JsonKey.RESPONSE, JsonKey.SUCCESS);
        }
        return response;
    }

    private Map<String, Object> createAddress(
            String userId, Map<String, Object> address) throws BaseException {
        address.put(JsonKey.USER_ID, userId);
        IUserAddressDao userAddressDao = (IUserAddressDao) UserDaoFactory.getDaoImpl(DaoImplType.USER_ADDRESS.getType());
        Map<String,Object> userAddressReq = new HashMap<>();
        userAddressReq.put(StringUtils.capitalize(JsonKey.ADDRESS),address);
        userAddressDao.createAddress(userAddressReq);
        return address;
    }
}
