package org.sunbird.organization.dao;

import java.util.Map;
import org.sunbird.common.factory.EsClientFactory;
import org.sunbird.dto.SearchDTO;
import org.sunbird.es.service.ElasticSearchService;
import org.sunbird.helper.ElasticSearchHelper;
import org.sunbird.helper.EsIndex;
import org.sunbird.response.Response;
import org.sunbird.util.jsonkey.JsonKey;
import scala.concurrent.Future;

public class OrgEsDaoImpl implements IOrgDao {

  private ElasticSearchService es =
      EsClientFactory.getInstance(EsClientFactory.EsClient.REST.getName());
  private static OrgEsDaoImpl INSTANCE = new OrgEsDaoImpl();

  private OrgEsDaoImpl() {
    throw new AssertionError();
  }

  public static OrgEsDaoImpl getInstance() {
    if (INSTANCE == null) {
      return new OrgEsDaoImpl();
    }
    return INSTANCE;
  }

  @Override
  public Response search(SearchDTO searchDTO) {
    Future<Map<String, Object>> future = es.search(searchDTO, EsIndex.ORG.getIndexName());
    Map<String, Object> responseMap = (Map) ElasticSearchHelper.getResponseFromFuture(future);
    return prepareEsSuccessResponse(responseMap);
  }

  private Response prepareEsSuccessResponse(Map<String, Object> responseMap) {
    Response response = new Response();
    response.put(JsonKey.RESPONSE, responseMap);
    return response;
  }

  /**
   * this method prevents class from deserialization
   *
   * @return
   */
  public OrgEsDaoImpl readResolve() {
    return getInstance();
  }
}
