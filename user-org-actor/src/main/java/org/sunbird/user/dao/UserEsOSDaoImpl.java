package org.sunbird.user.dao;

import org.sunbird.dto.SearchDtoMapper;
import org.sunbird.common.factory.EsClientFactory;
import org.sunbird.dto.SearchDTO;
import org.sunbird.es.service.ElasticSearchService;
import org.sunbird.helper.ElasticSearchHelper;
import org.sunbird.helper.EsIndex;
import org.sunbird.response.Response;
import org.sunbird.util.jsonkey.JsonKey;
import scala.concurrent.Future;

import java.util.Map;

public class UserEsOSDaoImpl implements IUserESDao {

    static ElasticSearchService es = EsClientFactory.getInstance(EsClientFactory.EsClient.REST.getName());
    Response response = null;

    @Override
    public Response getUserById(String userId) {
        Future<Map<String, Object>> future = es.getDataByIdentifier(EsIndex.USER.getIndexName(), userId);
        Map<String, Object> responseMap = (Map) ElasticSearchHelper.getResponseFromFuture(future);
        response = new Response();
        response.put(JsonKey.RESPONSE, responseMap);
        return response;
    }

    @Override
    public Response searchUser(Map<String, Object> request) {
        SearchDTO searchDTO = SearchDtoMapper.createSearchDto(request);
        Future<Map<String, Object>> future = es.search(searchDTO, EsIndex.USER.getIndexName());
        Map<String, Object> responseMap = (Map) ElasticSearchHelper.getResponseFromFuture(future);
        response = new Response();
        response.getResult().put(JsonKey.RESPONSE, responseMap);
        return response;
    }
}
