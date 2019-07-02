package org.sunbird.dto;

import org.sunbird.dto.SearchDTO;

import java.math.BigInteger;
import java.util.*;

/**
 * This class will be used to map the SearchDto
 *
 * @author anmolgupta
 */


public class SearchDtoMapper {


    /**
     * this method will create SearchDto pojo object.
     * @param searchQueryMap
     * @return
     */
    public static SearchDTO createSearchDto(Map<String, Object> searchQueryMap) {
        SearchDTO search = new SearchDTO();
        search = searchForQueryInRequest(searchQueryMap, search);
        search = searchForQueryFieldsInRequest(searchQueryMap, search);
        search = searchForFacetsInRequest(searchQueryMap, search);
        search = searchForFieldsInRequest(searchQueryMap, search);
        search = searchForFiltersInRequest(searchQueryMap, search);
        search = searchForExistsInRequest(searchQueryMap, search);
        search = searchForNotExistsInRequest(searchQueryMap, search);
        search = searchForSortByInRequest(searchQueryMap, search);
        search = searchForLimitInRequest(searchQueryMap, search);
        search = searchForOffsetInRequest(searchQueryMap, search);
        search = searchForSoftConstraintsInRequest(searchQueryMap, search);
        search = searchForGroupQueryInRequest(searchQueryMap, search);
        search = setSearchLimitInRequest(search);
        search = setSearchLimitWhenOffsetIsPresentInRequest(search);
        return search;
    }


    public static SearchDTO searchForQueryInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey("query")) {
            search.setQuery((String) searchQueryMap.get("query"));
            return search;
        }
        return search;
    }

    public static SearchDTO searchForQueryFieldsInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey("queryFields")) {
            search.setQueryFields((List) searchQueryMap.get("queryFields"));
        }
        return search;
    }

    public static SearchDTO searchForFacetsInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey("facets")) {
            search.setFacets((List) searchQueryMap.get("facets"));
        }
        return search;
    }

    public static SearchDTO searchForFieldsInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey("fields")) {
            search.setFields((List) searchQueryMap.get("fields"));
        }
        return search;
    }

    public static SearchDTO searchForFiltersInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey("filters")) {
            search.getAdditionalProperties().put("filters", searchQueryMap.get("filters"));
        }
        return search;
    }

    public static SearchDTO searchForExistsInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey("exists")) {
            search.getAdditionalProperties().put("exists", searchQueryMap.get("exists"));
        }
        return search;
    }

    public static SearchDTO searchForNotExistsInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey("not_exists")) {
            search.getAdditionalProperties().put("not_exists", searchQueryMap.get("not_exists"));
        }
        return search;
    }

    public static SearchDTO searchForSortByInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {

        if (searchQueryMap.containsKey("sort_by")) {
            search.getSortBy().putAll((Map) searchQueryMap.get("sort_by"));
        }
        return search;
    }

    public static SearchDTO searchForOffsetInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {

        if (searchQueryMap.containsKey("offset")) {
            if (searchQueryMap.get("offset") instanceof Integer) {
                search.setOffset((Integer) searchQueryMap.get("offset"));
            } else {
                search.setOffset(((BigInteger) searchQueryMap.get("offset")).intValue());
            }
        }
        return search;
    }

    public static SearchDTO searchForLimitInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {

        if (searchQueryMap.containsKey("limit")) {
            if (searchQueryMap.get("limit") instanceof Integer) {
                search.setLimit((Integer) searchQueryMap.get("limit"));
            } else {
                search.setLimit(((BigInteger) searchQueryMap.get("limit")).intValue());
            }
        }
        return search;
    }

    public static SearchDTO searchForGroupQueryInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {

        if (searchQueryMap.containsKey("groupQuery")) {
            search.getGroupQuery().addAll((Collection) searchQueryMap.get("groupQuery"));
        }
        return search;
    }

    public static SearchDTO searchForSoftConstraintsInRequest(Map<String, Object> searchQueryMap, SearchDTO search) {

        if (searchQueryMap.containsKey("softConstraints")) {
            Map<String, Integer> constraintsMap = new HashMap();
            Set<Map.Entry<String, BigInteger>> entrySet = ((Map) searchQueryMap.get("softConstraints")).entrySet();
            Iterator itr = entrySet.iterator();

            while (itr.hasNext()) {
                Map.Entry<String, BigInteger> entry = (Map.Entry) itr.next();
                constraintsMap.put(entry.getKey(), ((BigInteger) entry.getValue()).intValue());
            }
            search.setSoftConstraints(constraintsMap);
        }
        return search;
    }

    public static SearchDTO setSearchLimitInRequest(SearchDTO search) {

        if (search.getLimit() > 10000) {
            search.setLimit(10000);
        }
        return search;
    }

    public static SearchDTO setSearchLimitWhenOffsetIsPresentInRequest(SearchDTO search) {

        if (search.getLimit() + search.getOffset() > 10000) {
            search.setLimit(10000 - search.getOffset());
        }
        return search;
    }


}
