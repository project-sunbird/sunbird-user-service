package org.sunbird.common.factory;

import org.sunbird.es.service.ElasticSearchService;
import org.sunbird.es.service.impl.ElasticSearchRestHighImpl;
import org.sunbird.es.service.impl.ElasticSearchTcpImpl;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;

/**
 * This class is responsible for providing ES client object. it will provide you either restClient
 * or tcp client
 *
 * @author manzarul
 */
public class EsClientFactory {

  private static ElasticSearchService tcpClient = null;
  private static ElasticSearchService restClient = null;

  static {
    getTcpClient();
    getRestClient();
  }

  public enum EsClient {
    TCP("tcp"),
    REST("rest");

    private String name;

    private EsClient(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

  /**
   * This method return REST/TCP client for elastic search
   *
   * @param type can be "tcp" or "rest"
   * @return ElasticSearchService with the respected type impl
   */
  public static ElasticSearchService getInstance(String type) {
    if (EsClient.TCP.getName().equals(type)) {
      return getTcpClient();
    } else if (EsClient.REST.getName().equals(type)) {
      return getRestClient();
    } else {
      ProjectLogger.log(
          "EsClientFactory:getInstance: value for client type provided null ", LoggerEnum.ERROR);
    }
    return null;
  }

  private static ElasticSearchService getTcpClient() {
    tcpClient = new ElasticSearchTcpImpl();
    return tcpClient;
  }

  private static ElasticSearchService getRestClient() {
    restClient = new ElasticSearchRestHighImpl();
    return restClient;
  }
}
