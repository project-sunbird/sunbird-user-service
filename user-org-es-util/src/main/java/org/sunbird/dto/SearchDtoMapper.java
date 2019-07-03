package org.sunbird.dto;

import java.math.BigInteger;
import java.util.*;

/**
 * This class will be used to map the SearchDto
 *
 * @author anmolgupta
 */


public class SearchDtoMapper {
    private SearchDTO search;
    private Map<String, Object> searchQueryMap = null;
    private static final String EXISTS = "exists";
    private static final String NOT_EXISTS = "not_exists";
    private static final String FILTERS = "filters";
    private static final String GROUP_QUERY = "groupQuery";
    private static final String SOFT_CONSTRAINTS = "softConstraints";
    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";
    private static final String QUERY = "query";
    private static final String QUERY_FIELDS = "queryFields";
    private static final String FACETS = "facets";
    private static final String FIELDS = "fields";
    private static final String SORT_BY = "sort_by";

    private SearchDtoMapper() {
    }


    /**
     * this method will only return the instance for the class.
     *
     * @return
     */
    public static SearchDtoMapper getInstance() {
        return new SearchDtoMapper();
    }

    /**
     * this method will create SearchDto POJO object.
     *
     * @return
     */
    public SearchDTO map(Map<String, Object> map) {
        this.searchQueryMap = map;
        search = new SearchDTO();
        setQuery();
        setQueryFields();
        setFacets();
        setFields();
        setFilters();
        setExists();
        setNotExists();
        setSortBy();
        setLimit();
        setOffset();
        setSoftConstraints();
        setGroupQuery();
        setSearchLimit();
        setSearchLimitWithOffset();
        return search;
    }


    private void setQuery() {
        if (searchQueryMap.containsKey(QUERY)) {
            search.setQuery((String) searchQueryMap.get(QUERY));
        }
    }

    private void setQueryFields() {
        if (searchQueryMap.containsKey(QUERY_FIELDS)) {
            search.setQueryFields((List) searchQueryMap.get(QUERY_FIELDS));
        }


    }

    private void setFacets() {
        if (searchQueryMap.containsKey(
                FACETS)) {
            search.setFacets((List) searchQueryMap.get(FACETS));
        }


    }

    private void setFields() {
        if (searchQueryMap.containsKey(FIELDS)) {
            search.setFields((List) searchQueryMap.get(FIELDS));
        }


    }

    private void setFilters() {
        if (searchQueryMap.containsKey(FILTERS)) {
            search.getAdditionalProperties().put(FILTERS, searchQueryMap.get(FILTERS));
        }


    }

    private void setExists() {
        if (searchQueryMap.containsKey(EXISTS)) {
            search.getAdditionalProperties().put(EXISTS, searchQueryMap.get(EXISTS));
        }


    }

    private void setNotExists() {
        if (searchQueryMap.containsKey(NOT_EXISTS)) {
            search.getAdditionalProperties().put(NOT_EXISTS, searchQueryMap.get(NOT_EXISTS));
        }


    }

    private void setSortBy() {

        if (searchQueryMap.containsKey(SORT_BY)) {
            search.getSortBy().putAll((Map) searchQueryMap.get(SORT_BY));
        }


    }

    private void setOffset() {

        if (searchQueryMap.containsKey(OFFSET)) {
            if (searchQueryMap.get(OFFSET) instanceof Integer) {
                search.setOffset((Integer) searchQueryMap.get(OFFSET));
            } else {
                search.setOffset(((BigInteger) searchQueryMap.get(OFFSET)).intValue());
            }
        }


    }

    /**
     * by default if not limit is passed default limit set to 250
     *
     */
    private void setLimit() {

        if (searchQueryMap.containsKey(
                LIMIT)) {
            if (searchQueryMap.get(LIMIT) instanceof Integer) {
                search.setLimit((Integer) searchQueryMap.get(LIMIT));
            } else {
                search.setLimit(((BigInteger) searchQueryMap.get(LIMIT)).intValue());
            }
        }


    }

    private void setGroupQuery() {

        if (searchQueryMap.containsKey(
                GROUP_QUERY)) {
            search.getGroupQuery().addAll((Collection) searchQueryMap.get(GROUP_QUERY));
        }

    }

    private void setSoftConstraints() {

        if (searchQueryMap.containsKey(
                SOFT_CONSTRAINTS)) {
            Map<String, Integer> constraintsMap = new HashMap();
            Set<Map.Entry<String, BigInteger>> entrySet = ((Map) searchQueryMap.get(SOFT_CONSTRAINTS)).entrySet();
            Iterator itr = entrySet.iterator();

            while (itr.hasNext()) {
                Map.Entry<String, BigInteger> entry = (Map.Entry) itr.next();
                constraintsMap.put(entry.getKey(), ((BigInteger) entry.getValue()).intValue());
            }
            search.setSoftConstraints(constraintsMap);
        }
    }

    private void setSearchLimit() {

        if (search.getLimit() > 10000) {
            search.setLimit(10000);
        }
    }

    private void setSearchLimitWithOffset() {

        if (search.getLimit() + search.getOffset() > 10000) {
            search.setLimit(10000 - search.getOffset());
        }
    }

}
