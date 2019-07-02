package org.sunbird.dto;

import org.sunbird.helper.EsConstant;

import java.math.BigInteger;
import java.util.*;

/**
 * This class will be used to map the SearchDto
 *
 * @author anmolgupta
 */


public class SearchDtoMapper {


    public static SearchDtoMapper getInstance() {
        return new SearchDtoMapper();
    }

    /**
     * this method will create SearchDto pojo object.
     *
     * @param searchQueryMap
     * @return
     */
    public SearchDTO createSearchDto(Map<String, Object> searchQueryMap) {
        SearchDTO search = new SearchDTO();
        setQuery(searchQueryMap, search);
        setQueryFields(searchQueryMap, search);
        setFacets(searchQueryMap, search);
        setFields(searchQueryMap, search);
        setFilters(searchQueryMap, search);
        setExists(searchQueryMap, search);
        setNotExists(searchQueryMap, search);
        setSortBy(searchQueryMap, search);
        setLimit(searchQueryMap, search);
        setOffset(searchQueryMap, search);
        setSoftConstraints(searchQueryMap, search);
        setGroupQuery(searchQueryMap, search);
        setSearchLimit(search);
        setSearchLimitWithOffset(search);
        return search;
    }


    public SearchDTO setQuery(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey(EsConstant.QUERY)) {
            search.setQuery((String) searchQueryMap.get(EsConstant.QUERY));
            return search;
        }
        return search;
    }

    public SearchDTO setQueryFields(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey(EsConstant.QUERY_FIELDS)) {
            search.setQueryFields((List) searchQueryMap.get(EsConstant.QUERY_FIELDS));
        }
        return search;
    }

    public SearchDTO setFacets(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey(EsConstant.FACETS)) {
            search.setFacets((List) searchQueryMap.get(EsConstant.FACETS));
        }
        return search;
    }

    public SearchDTO setFields(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey(EsConstant.FIELDS)) {
            search.setFields((List) searchQueryMap.get(EsConstant.FIELDS));
        }
        return search;
    }

    public SearchDTO setFilters(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey(EsConstant.FILTERS)) {
            search.getAdditionalProperties().put(EsConstant.FILTERS, searchQueryMap.get(EsConstant.FILTERS));
        }
        return search;
    }

    public SearchDTO setExists(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey(EsConstant.EXISTS)) {
            search.getAdditionalProperties().put(EsConstant.EXISTS, searchQueryMap.get(EsConstant.EXISTS));
        }
        return search;
    }

    public SearchDTO setNotExists(Map<String, Object> searchQueryMap, SearchDTO search) {
        if (searchQueryMap.containsKey(EsConstant.NOT_EXISTS)) {
            search.getAdditionalProperties().put(EsConstant.NOT_EXISTS, searchQueryMap.get(EsConstant.NOT_EXISTS));
        }
        return search;
    }

    public SearchDTO setSortBy(Map<String, Object> searchQueryMap, SearchDTO search) {

        if (searchQueryMap.containsKey(EsConstant.SORT_BY)) {
            search.getSortBy().putAll((Map) searchQueryMap.get(EsConstant.SORT_BY));
        }
        return search;
    }

    public SearchDTO setOffset(Map<String, Object> searchQueryMap, SearchDTO search) {

        if (searchQueryMap.containsKey(EsConstant.OFFSET)) {
            if (searchQueryMap.get(EsConstant.OFFSET) instanceof Integer) {
                search.setOffset((Integer) searchQueryMap.get(EsConstant.OFFSET));
            } else {
                search.setOffset(((BigInteger) searchQueryMap.get(EsConstant.OFFSET)).intValue());
            }
        }
        return search;
    }

    public SearchDTO setLimit(Map<String, Object> searchQueryMap, SearchDTO search) {

        if (searchQueryMap.containsKey(EsConstant.LIMIT)) {
            if (searchQueryMap.get(EsConstant.LIMIT) instanceof Integer) {
                search.setLimit((Integer) searchQueryMap.get(EsConstant.LIMIT));
            } else {
                search.setLimit(((BigInteger) searchQueryMap.get(EsConstant.LIMIT)).intValue());
            }
        }
        return search;
    }

    public SearchDTO setGroupQuery(Map<String, Object> searchQueryMap, SearchDTO search) {

        if (searchQueryMap.containsKey(EsConstant.GROUP_QUERY)) {
            search.getGroupQuery().addAll((Collection) searchQueryMap.get(EsConstant.GROUP_QUERY));
        }
        return search;
    }

    public SearchDTO setSoftConstraints(Map<String, Object> searchQueryMap, SearchDTO search) {

        if (searchQueryMap.containsKey(EsConstant.SOFT_CONSTRAINTS)) {
            Map<String, Integer> constraintsMap = new HashMap();
            Set<Map.Entry<String, BigInteger>> entrySet = ((Map) searchQueryMap.get(EsConstant.SOFT_CONSTRAINTS)).entrySet();
            Iterator itr = entrySet.iterator();

            while (itr.hasNext()) {
                Map.Entry<String, BigInteger> entry = (Map.Entry) itr.next();
                constraintsMap.put(entry.getKey(), ((BigInteger) entry.getValue()).intValue());
            }
            search.setSoftConstraints(constraintsMap);
        }
        return search;
    }

    public SearchDTO setSearchLimit(SearchDTO search) {

        if (search.getLimit() > 10000) {
            search.setLimit(10000);
        }
        return search;
    }

    public SearchDTO setSearchLimitWithOffset(SearchDTO search) {

        if (search.getLimit() + search.getOffset() > 10000) {
            search.setLimit(10000 - search.getOffset());
        }
        return search;
    }


}
