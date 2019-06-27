package org.sunbird.es.service.impl;

import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Map;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Assert;
import org.powermock.api.mockito.PowerMockito;
import org.sunbird.helper.ConnectionManager;

public class RestTestHelper {

  private static RestHighLevelClient restClient = null;
  public static Map<String, Object> chemistryMap = null;
  public static Map<String, Object> physicsMap = null;
  public static final String INDEX_NAME = "sbtestindex";
  public static final String TYPE_NAME = "sbtesttype";
  public static final String STARTS_WITH = "startsWith";
  public static final String ENDS_WITH = "endsWith";
  public static final long START_TIME = System.currentTimeMillis();

  public void mockBaseRules() {
    restClient = mock(RestHighLevelClient.class);
    PowerMockito.mockStatic(ConnectionManager.class);
    try {
      doNothing().when(ConnectionManager.class, "registerShutDownHook");
    } catch (Exception e) {
      Assert.fail("Initialization of test case failed due to " + e.getLocalizedMessage());
    }
    when(ConnectionManager.getRestClient()).thenReturn(restClient);
  }
}
