package org.sunbird.es.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.util.concurrent.FutureUtils;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sunbird.common.factory.EsClientFactory;
import org.sunbird.dto.SearchDTO;
import org.sunbird.es.data.TestData;
import org.sunbird.es.service.ElasticSearchService;
import org.sunbird.helper.ConnectionManager;
import org.sunbird.helper.ElasticSearchHelper;
import org.sunbird.helper.EsConstant;
import scala.concurrent.Future;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*", "javax.net.ssl.*", "javax.security.*"})
@PrepareForTest({
  ConnectionManager.class,
  TransportClient.class,
  AcknowledgedResponse.class,
  GetRequestBuilder.class,
  BulkProcessor.class,
  FutureUtils.class,
  SearchHit.class,
  SearchHits.class,
  Aggregations.class
})
@SuppressStaticInitializationFor({"org.sunbird.common.ConnectionManager"})
public class ElasticSearchTcpImplTest extends TCPTestHelper {
  private ElasticSearchService esService =
      EsClientFactory.getInstance(EsClientFactory.EsClient.TCP.getName());

  @BeforeClass
  public static void initClass() throws Exception {
    chemistryMap = TestData.initializeChemistryCourse(5);
    physicsMap = TestData.intitializePhysicsCourse(60);
  }

  @Before
  public void initBeforeTest() {
    mockBaseRules();
    mockRulesForGet();
    mockRulesForInsert();
    mockRulesForUpdate();
    mockRulesForDelete();
    mockRulesForIndexes();
    mockRulesBulkInsert();
  }

  @Test
  public void testCreateDataSuccess() {
    mockRulesForInsert();

    esService.save(INDEX_NAME, (String) chemistryMap.get("courseId"), chemistryMap);
    assertNotNull(chemistryMap.get("courseId"));

    esService.save(INDEX_NAME, (String) physicsMap.get("courseId"), physicsMap);
    assertNotNull(physicsMap.get("courseId"));
  }

  @Test
  public void testCreateDataFailureWithEmptyIdentifier() {
    mockRulesForInsert();
    Future<String> ft = esService.save(INDEX_NAME, "", chemistryMap);
    Object object = ElasticSearchHelper.getResponseFromFuture(ft);
    assertEquals("ERROR", object);
  }

  @Test
  public void testGetByIdentifierSuccess() {
    Future<Map<String, Object>> responseMapF =
        esService.getDataByIdentifier(INDEX_NAME, (String) chemistryMap.get("courseId"));

    Map<String, Object> responseMap =
        (Map<String, Object>) ElasticSearchHelper.getResponseFromFuture(responseMapF);

    assertEquals(responseMap.get("courseId"), chemistryMap.get("courseId"));
  }

  @Test
  public void testGetByIdentifierFailure() {
    Future<Map<String, Object>> responseMapF = esService.getDataByIdentifier(INDEX_NAME, "dummyId");

    Map<String, Object> responseMap =
        (Map<String, Object>) ElasticSearchHelper.getResponseFromFuture(responseMapF);

    Assert.assertNotEquals(responseMap.get("id"), "dummyId");
  }

  @Test
  public void testUpdateDataSuccess() {
    Map<String, Object> innermap = new HashMap<>();
    innermap.put("courseName", "Updated course name");
    innermap.put("organisationId", "updatedOrgId");

    GetRequestBuilder grb = mock(GetRequestBuilder.class);
    GetResponse getResponse = mock(GetResponse.class);
    when(client.prepareGet(
            Mockito.anyString(),
            Mockito.anyString(),
            Mockito.eq((String) chemistryMap.get("courseId"))))
        .thenReturn(grb);
    when(grb.get()).thenReturn(getResponse);
    when(getResponse.getSource()).thenReturn(innermap);

    Future<Boolean> responseF =
        esService.update(INDEX_NAME, (String) chemistryMap.get("courseId"), innermap);
    boolean response = (boolean) ElasticSearchHelper.getResponseFromFuture(responseF);
    assertTrue(response);
  }

  @Test
  public void testUpdateDataFailureWithNullIdentifier() {
    Map<String, Object> innermap = new HashMap<>();
    innermap.put("courseName", "Updated course name");
    innermap.put("organisationId", "updatedOrgId");

    GetRequestBuilder grb = mock(GetRequestBuilder.class);
    GetResponse getResponse = mock(GetResponse.class);
    when(client.prepareGet(
            Mockito.anyString(),
            Mockito.anyString(),
            Mockito.eq((String) chemistryMap.get("courseId"))))
        .thenReturn(grb);
    when(grb.get()).thenReturn(getResponse);
    when(getResponse.getSource()).thenReturn(innermap);

    Future<Boolean> responseF = esService.update(INDEX_NAME, null, innermap);
    boolean response = (boolean) ElasticSearchHelper.getResponseFromFuture(responseF);
    assertFalse(response);
  }

  @Test
  public void testUpdateDataFailureWithEmptyBody() {
    Map<String, Object> innermap = new HashMap<>();
    innermap.put("courseName", "Updated course name");
    innermap.put("organisationId", "updatedOrgId");

    GetRequestBuilder grb = mock(GetRequestBuilder.class);
    GetResponse getResponse = mock(GetResponse.class);
    when(client.prepareGet(
            Mockito.anyString(),
            Mockito.anyString(),
            Mockito.eq((String) chemistryMap.get("courseId"))))
        .thenReturn(grb);
    when(grb.get()).thenReturn(getResponse);
    when(getResponse.getSource()).thenReturn(innermap);

    Future<Boolean> responseF =
        esService.update(
            INDEX_NAME, (String) chemistryMap.get("courseId"), new HashMap<String, Object>());
    boolean response = (boolean) ElasticSearchHelper.getResponseFromFuture(responseF);
    assertFalse(response);
  }

  @Test
  public void testComplexSearchSuccess() throws Exception {
    SearchDTO searchDTO = new SearchDTO();

    List<String> fields = new ArrayList<String>();
    fields.add("courseId");
    fields.add("courseType");
    fields.add("createdOn");
    fields.add("description");

    Map<String, Object> sortMap = new HashMap<>();
    sortMap.put("courseType", "ASC");
    searchDTO.setSortBy(sortMap);

    List<String> excludedFields = new ArrayList<String>();
    excludedFields.add("createdOn");
    searchDTO.setExcludedFields(excludedFields);

    searchDTO.setLimit(20);
    searchDTO.setOffset(0);

    Map<String, Object> additionalPro = new HashMap<String, Object>();
    searchDTO.addAdditionalProperty("test", additionalPro);

    List<String> existsList = new ArrayList<String>();
    existsList.add("pkgVersion");
    existsList.add("size");

    Map<String, Object> additionalProperties = new HashMap<String, Object>();
    additionalProperties.put(EsConstant.EXISTS, existsList);

    List<String> description = new ArrayList<String>();
    description.add("This is for chemistry");
    description.add("Hindi Jii");

    List<Integer> sizes = new ArrayList<Integer>();
    sizes.add(10);
    sizes.add(20);

    Map<String, Object> filterMap = new HashMap<String, Object>();
    filterMap.put("description", description);
    filterMap.put("size", sizes);
    additionalProperties.put(EsConstant.FILTERS, filterMap);

    Map<String, Object> rangeMap = new HashMap<String, Object>();
    rangeMap.put(">", 0);
    filterMap.put("pkgVersion", rangeMap);

    Map<String, Object> lexicalMap = new HashMap<>();
    lexicalMap.put(STARTS_WITH, "type");
    filterMap.put("courseType", lexicalMap);
    Map<String, Object> lexicalMap1 = new HashMap<>();
    lexicalMap1.put(ENDS_WITH, "sunbird");
    filterMap.put("courseAddedByName", lexicalMap1);
    filterMap.put("orgName", "Name of the organisation");

    searchDTO.setAdditionalProperties(additionalProperties);
    searchDTO.setFields(fields);
    searchDTO.setQuery("organisation");

    List<String> mode = Arrays.asList("soft");
    searchDTO.setMode(mode);
    Map<String, Integer> constraintMap = new HashMap<String, Integer>();
    constraintMap.put("grades", 10);
    constraintMap.put("pkgVersion", 5);
    searchDTO.setSoftConstraints(constraintMap);
    searchDTO.setQuery("organisation Name published");
    mockRulesForSearch(3);
    Future<Map<String, Object>> map = esService.search(searchDTO, INDEX_NAME);
    Map<String, Object> response =
        (Map<String, Object>) ElasticSearchHelper.getResponseFromFuture(map);
    assertEquals(2, response.size());
  }

  @Test
  public void testComplexSearchSuccessWithRangeGreaterThan() {
    SearchDTO searchDTO = new SearchDTO();
    Map<String, Object> additionalProperties = new HashMap<String, Object>();
    List<Integer> sizes = new ArrayList<Integer>();
    sizes.add(10);
    sizes.add(20);
    Map<String, Object> filterMap = new HashMap<String, Object>();
    filterMap.put("size", sizes);
    Map<String, String> innerMap = new HashMap<>();
    innerMap.put("createdOn", "2017-11-06");
    filterMap.put(">=", innerMap);
    additionalProperties.put(EsConstant.FILTERS, filterMap);
    Map<String, Object> rangeMap = new HashMap<String, Object>();
    rangeMap.put(">", 0);
    filterMap.put("pkgVersion", rangeMap);
    Map<String, Object> lexicalMap = new HashMap<>();
    lexicalMap.put(STARTS_WITH, "type");
    filterMap.put("courseType", lexicalMap);
    Map<String, Object> lexicalMap1 = new HashMap<>();
    lexicalMap1.put(ENDS_WITH, "sunbird");
    filterMap.put("courseAddedByName", lexicalMap1);
    filterMap.put("orgName", "Name of the organisation");

    searchDTO.setAdditionalProperties(additionalProperties);
    searchDTO.setQuery("organisation");
    mockRulesForSearch(3);

    Future<Map<String, Object>> map = esService.search(searchDTO, INDEX_NAME);
    Map<String, Object> response =
        (Map<String, Object>) ElasticSearchHelper.getResponseFromFuture(map);
    assertEquals(2, response.size());
  }

  @Test
  public void testComplexSearchSuccessWithRangeLessThan() {
    SearchDTO searchDTO = new SearchDTO();
    Map<String, Object> additionalProperties = new HashMap<String, Object>();
    List<Integer> sizes = new ArrayList<Integer>();
    sizes.add(10);
    sizes.add(20);
    Map<String, Object> filterMap = new HashMap<String, Object>();
    filterMap.put("size", sizes);
    Map<String, String> innerMap = new HashMap<>();
    innerMap.put("createdOn", "2017-11-06");
    filterMap.put("<=", innerMap);
    additionalProperties.put(EsConstant.FILTERS, filterMap);
    Map<String, Object> rangeMap = new HashMap<String, Object>();
    rangeMap.put(">", 0);
    filterMap.put("pkgVersion", rangeMap);
    Map<String, Object> lexicalMap = new HashMap<>();
    lexicalMap.put(STARTS_WITH, "type");
    filterMap.put("courseType", lexicalMap);
    Map<String, Object> lexicalMap1 = new HashMap<>();
    lexicalMap1.put(ENDS_WITH, "sunbird");
    filterMap.put("courseAddedByName", lexicalMap1);
    filterMap.put("orgName", "Name of the organisation");

    searchDTO.setAdditionalProperties(additionalProperties);
    searchDTO.setQuery("organisation");
    mockRulesForSearch(3);
    Future<Map<String, Object>> map = esService.search(searchDTO, INDEX_NAME);
    Map<String, Object> response =
        (Map<String, Object>) ElasticSearchHelper.getResponseFromFuture(map);
    assertEquals(2, response.size());
  }

  @Test
  public void testGetByIdentifierFailureWithoutIndex() {

    Future<Map<String, Object>> responseMap =
        esService.getDataByIdentifier(null, (String) chemistryMap.get("courseId"));
    Map<String, Object> map =
        (HashMap<String, Object>) ElasticSearchHelper.getResponseFromFuture(responseMap);
    assertTrue(map.size() == 0);
  }

  @Test
  public void testGetByIdentifierFailureWithoutTypeAndIndexIdentifier() {
    Future<Map<String, Object>> responseMap = esService.getDataByIdentifier(null, "");
    Map<String, Object> map =
        (Map<String, Object>) ElasticSearchHelper.getResponseFromFuture(responseMap);
    assertEquals(0, map.size());
  }

  @Test
  public void testGetDataByIdentifierFailureWithoutIdentifier() {
    Future<Map<String, Object>> responseMap = esService.getDataByIdentifier(INDEX_NAME, "");
    Map<String, Object> map =
        (Map<String, Object>) ElasticSearchHelper.getResponseFromFuture(responseMap);
    assertEquals(0, map.size());
  }

  @Test
  public void testUpdateDataFailureWithoutIdentifier() {
    Map<String, Object> innermap = new HashMap<>();
    innermap.put("courseName", "Updated Course Name");
    innermap.put("organisationId", "updatedOrgId");
    Future<Boolean> response = esService.update(INDEX_NAME, null, innermap);
    boolean result = (boolean) ElasticSearchHelper.getResponseFromFuture(response);
    assertFalse(result);
  }

  @Test
  public void testUpdateDataFailureWithEmptyMap() {
    Map<String, Object> innermap = new HashMap<>();
    Future<Boolean> response =
        esService.update(INDEX_NAME, (String) chemistryMap.get("courseId"), innermap);
    boolean result = (boolean) ElasticSearchHelper.getResponseFromFuture(response);
    assertFalse(result);
  }

  @Test
  public void testUpdateDataFailureWithNullMap() {
    Future<Boolean> response =
        esService.update(INDEX_NAME, (String) chemistryMap.get("courseId"), null);
    boolean result = (boolean) ElasticSearchHelper.getResponseFromFuture(response);
    assertFalse(result);
  }

  @Test
  public void testUpsertDataFailureWithoutIdentifier() {
    Map<String, Object> innermap = new HashMap<>();
    innermap.put("courseName", "Updated Course Name");
    innermap.put("organisationId", "updatedOrgId");
    Future<Boolean> response = esService.upsert(INDEX_NAME, null, innermap);
    boolean result = (boolean) ElasticSearchHelper.getResponseFromFuture(response);
    assertFalse(result);
  }

  @Test
  public void testUpsertDataFailureWithoutIndex() {
    Map<String, Object> innermap = new HashMap<>();
    innermap.put("courseName", "Updated Course Name");
    innermap.put("organisationId", "updatedOrgId");
    Future<Boolean> response =
        esService.upsert(null, (String) chemistryMap.get("courseId"), innermap);
    boolean result = (boolean) ElasticSearchHelper.getResponseFromFuture(response);
    assertFalse(result);
  }

  @Test
  public void testUpsertDataFailureWithEmptyMap() {
    Map<String, Object> innermap = new HashMap<>();
    Future<Boolean> response =
        esService.upsert(INDEX_NAME, (String) chemistryMap.get("courseId"), innermap);
    boolean result = (boolean) ElasticSearchHelper.getResponseFromFuture(response);
    assertFalse(result);
  }

  @Test
  public void testSaveDataFailureWithoutIndexName() {
    Future<String> response =
        esService.save("", (String) chemistryMap.get("courseId"), chemistryMap);
    String result = (String) ElasticSearchHelper.getResponseFromFuture(response);
    assertEquals("ERROR", result);
  }

  @Test
  public void testGetDataByIdentifierFailureByEmptyIdentifier() {
    Future<Map<String, Object>> responseMap = esService.getDataByIdentifier(INDEX_NAME, "");
    Map<String, Object> response =
        (Map<String, Object>) ElasticSearchHelper.getResponseFromFuture(responseMap);
    assertEquals(0, response.size());
  }

  @Test
  public void testRemoveDataSuccessByIdentifier() {
    Future<Boolean> response = esService.delete(INDEX_NAME, (String) chemistryMap.get("courseId"));
    boolean result = (boolean) ElasticSearchHelper.getResponseFromFuture(response);
    assertEquals(true, result);
  }

  @Test
  public void testRemoveDataFailureByIdentifierEmpty() {
    Future<Boolean> response = esService.delete(INDEX_NAME, "");
    boolean result = (boolean) ElasticSearchHelper.getResponseFromFuture(response);
    assertEquals(false, result);
  }

  @Test
  public void testInitialiseConnectionFailureFromProperties() {
    boolean response =
        ConnectionManager.initialiseConnectionFromPropertiesFile(
            "Test", "localhost1,128.0.0.1", "9200,9300");
    assertFalse(response);
  }

  @Test
  public void testHealthCheckSuccess() {
    Future<Boolean> response = esService.healthCheck();
    boolean result = (boolean) ElasticSearchHelper.getResponseFromFuture(response);
    assertEquals(true, result);
  }

  @Test
  public void testUpsertDataSuccess() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("test", "test");
    Future<Boolean> response = esService.upsert(INDEX_NAME, "test-12349", data);
    boolean result = (boolean) ElasticSearchHelper.getResponseFromFuture(response);
    assertEquals(true, result);
  }

  @Test
  public void testBulkInsertDataSuccess() {
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("test1", "test");
    data.put("test2", "manzarul");
    List<Map<String, Object>> listOfMap = new ArrayList<Map<String, Object>>();
    listOfMap.add(data);
    Future<Boolean> response = esService.bulkInsert(INDEX_NAME, listOfMap);
    boolean result = (boolean) ElasticSearchHelper.getResponseFromFuture(response);
    assertEquals(true, result);
  }
}
