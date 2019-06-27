package org.sunbird.es.service.impl;

import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequestBuilder;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Assert;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.sunbird.helper.ConnectionManager;

public class TCPTestHelper {
  public static TransportClient client = null;
  public static Map<String, Object> chemistryMap = null;
  public static Map<String, Object> physicsMap = null;
  public static final String INDEX_NAME = "sbtestindex";
  public static final String TYPE_NAME = "sbtesttype";
  public static final String STARTS_WITH = "startsWith";
  public static final String ENDS_WITH = "endsWith";
  public static final long START_TIME = System.currentTimeMillis();

  public void mockBaseRules() {
    client = mock(TransportClient.class);
    PowerMockito.mockStatic(ConnectionManager.class);
    try {
      doNothing().when(ConnectionManager.class, "registerShutDownHook");
    } catch (Exception e) {
      Assert.fail("Initialization of test case failed due to " + e.getLocalizedMessage());
    }
    when(ConnectionManager.getClient()).thenReturn(client);
  }

  public static void mockRulesForGet() {
    mockRulesForGet(false);
  }

  public static void mockRulesForGet(boolean expectedEmptyMap) {
    GetRequestBuilder grb = mock(GetRequestBuilder.class);
    GetResponse gResp = mock(GetResponse.class);
    when(client.prepareGet(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
        .thenReturn(grb);

    when(client.prepareGet()).thenReturn(grb);
    when(grb.setIndex(Mockito.anyString())).thenReturn(grb);
    when(grb.setId(Mockito.anyString())).thenReturn(grb);
    when(grb.get()).thenReturn(gResp);
    Map expMap = expectedEmptyMap ? Collections.emptyMap() : chemistryMap;
    when(gResp.getSource()).thenReturn(expMap);
  }

  public void mockRulesForSearch(long expectedValue) {
    SearchRequestBuilder srb = mock(SearchRequestBuilder.class);
    ListenableActionFuture<SearchResponse> lstActFtr = mock(ListenableActionFuture.class);
    List<SearchHit> lst = new ArrayList<>();
    SearchHit hit1 = mock(SearchHit.class);
    lst.add(hit1);

    SearchResponse searchResponse = mock(SearchResponse.class);
    Aggregations aggregations = mock(Aggregations.class);
    Terms terms = mock(Terms.class);
    Histogram histogram = mock(Histogram.class);
    SearchHits searchHits = mock(SearchHits.class);

    when(client.prepareSearch(Mockito.anyVararg())).thenReturn(srb);
    when(srb.setIndices(Mockito.anyVararg())).thenReturn(srb);
    when(srb.setTypes(Mockito.anyVararg())).thenReturn(srb);
    when(srb.addSort(Mockito.anyString(), Mockito.any(SortOrder.class))).thenReturn(srb);
    when(srb.execute()).thenReturn(lstActFtr);
    when(lstActFtr.actionGet()).thenReturn(searchResponse);
    when(searchResponse.getHits()).thenReturn(searchHits);
    when(searchResponse.getAggregations()).thenReturn(aggregations);
    when(aggregations.get(Mockito.eq("description"))).thenReturn(terms);
    //    when(aggregations.get(Mockito.eq("createdOn"))).thenReturn(histogram);
    when(terms.getBuckets()).thenReturn(new ArrayList<>());
    when(histogram.getBuckets()).thenReturn(new ArrayList<>());

    when(searchHits.getTotalHits()).thenReturn(expectedValue);

    when(searchHits.iterator()).thenReturn(lst.iterator());
    when(hit1.getSourceAsMap()).thenReturn(new HashMap());
  }

  public static void mockRulesForInsert() {
    IndexRequestBuilder irb = mock(IndexRequestBuilder.class);
    IndexResponse ir = mock(IndexResponse.class);

    when(client.prepareIndex(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
        .thenReturn(irb);
    when(irb.setSource(Mockito.anyMap())).thenReturn(irb);
    when(irb.get()).thenReturn(ir);
    when(ir.getId()).thenReturn((String) chemistryMap.get("courseId"));
  }

  public static void mockRulesForUpdate() {
    UpdateRequestBuilder urbForUpdate = mock(UpdateRequestBuilder.class);
    UpdateRequestBuilder urbForEmptyUpdate = mock(UpdateRequestBuilder.class);
    ActionFuture<UpdateResponse> actFtr = mock(ActionFuture.class);
    UpdateResponse updateResponse = mock(UpdateResponse.class);
    UpdateResponse updateForEmptyRespose = mock(UpdateResponse.class);

    when(client.prepareUpdate(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
        .thenReturn(urbForUpdate);
    when(urbForUpdate.setDoc(Mockito.anyMap())).thenReturn(urbForUpdate);
    when(urbForUpdate.get()).thenReturn(updateResponse);
    when(updateResponse.getResult()).thenReturn(Result.UPDATED);

    // Making sure update returns empty for empty response.
    when(urbForUpdate.setDoc(Mockito.eq(new HashMap<String, Object>())))
        .thenReturn(urbForEmptyUpdate);
    when(urbForEmptyUpdate.get()).thenReturn(updateForEmptyRespose);
    when(updateForEmptyRespose.getResult()).thenReturn(Result.NOOP);

    when(client.update(Mockito.any(UpdateRequest.class))).thenReturn(actFtr);
    try {
      when(actFtr.get()).thenReturn(updateResponse);
    } catch (InterruptedException | ExecutionException e) {
      Assert.fail("Initialization of test case failed due to " + e.getLocalizedMessage());
    }
  }

  public static void mockRulesForDelete() {
    DeleteRequestBuilder drb = mock(DeleteRequestBuilder.class);
    DeleteResponse delResponse = mock(DeleteResponse.class);
    when(client.prepareDelete(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
        .thenReturn(drb);
    when(drb.get()).thenReturn(delResponse);
    when(delResponse.getResult()).thenReturn(Result.DELETED);
  }

  public static void mockRulesForIndexes() {
    mockRulesForIndexes(true);
  }

  public static void mockRulesForIndexes(boolean mappingsDone) {

    IndicesAdminClient indicesAdminMock = mock(IndicesAdminClient.class);
    AdminClient adminMock = mock(AdminClient.class);
    RefreshRequestBuilder refReqBldr = mock(RefreshRequestBuilder.class);
    RefreshResponse refResponse = mock(RefreshResponse.class);
    ActionFuture actFtrType = mock(ActionFuture.class);
    ActionFuture actFtrIndex = mock(ActionFuture.class);
    IndicesExistsResponse indExistResponse = mock(IndicesExistsResponse.class);
    TypesExistsResponse typeExistsResponse = mock(TypesExistsResponse.class);

    CreateIndexRequestBuilder mockCreateIndexReqBldr = mock(CreateIndexRequestBuilder.class);
    PutMappingRequestBuilder mockPutMappingReqBldr = mock(PutMappingRequestBuilder.class);
    PutMappingResponse mockPutMappingResponse = mock(PutMappingResponse.class);
    CreateIndexResponse mockCreateIndResp = mock(CreateIndexResponse.class);

    doReturn(adminMock).when(client).admin();
    doReturn(indicesAdminMock).when(adminMock).indices();
    doReturn(actFtrType).when(indicesAdminMock).typesExists(Mockito.any(TypesExistsRequest.class));
    doReturn(actFtrIndex).when(indicesAdminMock).exists(Mockito.any(IndicesExistsRequest.class));
    doReturn(refReqBldr).when(indicesAdminMock).prepareRefresh(Mockito.anyVararg());

    doReturn(refResponse).when(refReqBldr).get();
    try {
      doReturn(indExistResponse).when(actFtrIndex).get();
      doReturn(true).when(indExistResponse).isExists();
    } catch (InterruptedException | ExecutionException e) {
      Assert.fail("Exception occurred " + e.getLocalizedMessage());
    }

    try {
      doReturn(typeExistsResponse).when(actFtrType).get();
      doReturn(true).when(typeExistsResponse).isExists();
    } catch (InterruptedException | ExecutionException e) {
      Assert.fail("Exception occurred " + e.getLocalizedMessage());
    }
    doReturn(mockCreateIndexReqBldr).when(indicesAdminMock).prepareCreate(Mockito.anyString());

    doReturn(mockCreateIndexReqBldr)
        .when(mockCreateIndexReqBldr)
        .setSettings(Mockito.any(Settings.class));

    doReturn(mockCreateIndResp).when(mockCreateIndexReqBldr).get();
    doReturn(true).when(mockCreateIndResp).isAcknowledged();
    doReturn(mockPutMappingReqBldr).when(indicesAdminMock).preparePutMapping(Mockito.anyString());
    doReturn(mockPutMappingReqBldr).when(indicesAdminMock).preparePutMapping(Mockito.anyString());
    doReturn(mockPutMappingReqBldr).when(mockPutMappingReqBldr).setSource(Mockito.anyString());
    doReturn(mockPutMappingReqBldr).when(mockPutMappingReqBldr).setType(Mockito.anyString());
    doReturn(mockPutMappingResponse).when(mockPutMappingReqBldr).get();
    doReturn(mappingsDone).when(mockPutMappingResponse).isAcknowledged();
  }

  public static void mockRulesBulkInsert() {
    PowerMockito.mockStatic(BulkProcessor.class);
    BulkProcessor.Builder bldr = mock(BulkProcessor.Builder.class);
    BulkProcessor bProcessor = mock(BulkProcessor.class);
    when(BulkProcessor.builder(Mockito.any(Client.class), Mockito.any(Listener.class)))
        .thenReturn(bldr);
    when(bldr.setBulkActions(Mockito.anyInt())).thenReturn(bldr);
    when(bldr.setConcurrentRequests(Mockito.anyInt())).thenReturn(bldr);
    when(bldr.build()).thenReturn(bProcessor);
    when(bProcessor.add(Mockito.any(IndexRequest.class))).thenReturn(bProcessor);
  }
}
