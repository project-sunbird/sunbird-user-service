package org.sunbird.user.dao;

import org.apache.commons.collections.MapUtils;
import org.sunbird.dto.SearchDtoMapper;
import org.sunbird.common.factory.EsClientFactory;
import org.sunbird.dto.SearchDTO;
import org.sunbird.es.service.ElasticSearchService;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.UserException;
import org.sunbird.exception.message.IResponseMessage;
import org.sunbird.exception.message.Localizer;
import org.sunbird.exception.message.ResponseCode;
import org.sunbird.helper.ElasticSearchHelper;
import org.sunbird.helper.EsIndex;
import org.sunbird.response.Response;
import org.sunbird.util.jsonkey.JsonKey;
import scala.concurrent.Future;

import java.util.Map;


/**
 * @author anmolgupta
 */
public class UserESDaoImpl implements IUserDao {

    private ElasticSearchService es = EsClientFactory.getInstance(EsClientFactory.EsClient.REST.getName());
    private Localizer localizer = Localizer.getInstance();


    private UserESDaoImpl() {
    }

    /**
     * this method is used to get the instance of the current class
     * @return
     */
    public static UserESDaoImpl getInstance() {
        return new UserESDaoImpl();
    }

    /**
     * this method  is used to get the user W.R.T id
     * @param userId
     * @return response
     * @throws BaseException
     */
    @Override
    public Response getUserById(String userId) throws BaseException {
        Future<Map<String, Object>> future = es.getDataByIdentifier(EsIndex.USER.getIndexName(), userId);
        Map<String, Object> responseMap = (Map) ElasticSearchHelper.getResponseFromFuture(future);
        if (MapUtils.isNotEmpty(responseMap)) {
            return prepareEsSuccessResponse(responseMap);
        } else {
            throw new UserException.UserNotFound(IResponseMessage.USER_NOT_FOUND, localizer.getMessage(IResponseMessage.USER_NOT_FOUND, null), ResponseCode.BAD_REQUEST.getCode());
        }
    }

    /**
     * this method is used to search the user W.R.T request
     * @param searchDTO
     * @return response
     */
    @Override
    public Response searchUser(SearchDTO searchDTO) {
        Future<Map<String, Object>> future = es.search(searchDTO, EsIndex.USER.getIndexName());
        Map<String, Object> responseMap = (Map) ElasticSearchHelper.getResponseFromFuture(future);
        return prepareEsSuccessResponse(responseMap);
    }


    private Response prepareEsSuccessResponse(Map<String, Object> responseMap) {
        Response response = new Response();
        response.put(JsonKey.RESPONSE, responseMap);
        return response;
    }



    @Override
    public Response createUser(Map<String, Object> user) throws BaseException {
        return null;
    }

}
