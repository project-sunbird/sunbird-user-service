package org.sunbird;

import org.sunbird.dto.SearchDTO;

import java.math.BigInteger;
import java.util.*;

public class SearchDtoMapper {


    public static SearchDTO createSearchDto(Map<String, Object> searchQueryMap) {
        SearchDTO search = new SearchDTO();
        if (searchQueryMap.containsKey("query")) {
            search.setQuery((String) searchQueryMap.get("query"));
        }

        if (searchQueryMap.containsKey("queryFields")) {
            search.setQueryFields((List) searchQueryMap.get("queryFields"));
        }

        if (searchQueryMap.containsKey("facets")) {
            search.setFacets((List) searchQueryMap.get("facets"));
        }

        if (searchQueryMap.containsKey("fields")) {
            search.setFields((List) searchQueryMap.get("fields"));
        }

        if (searchQueryMap.containsKey("filters")) {
            search.getAdditionalProperties().put("filters", searchQueryMap.get("filters"));
        }

        if (searchQueryMap.containsKey("exists")) {
            search.getAdditionalProperties().put("exists", searchQueryMap.get("exists"));
        }

        if (searchQueryMap.containsKey("not_exists")) {
            search.getAdditionalProperties().put("not_exists", searchQueryMap.get("not_exists"));
        }

        if (searchQueryMap.containsKey("sort_by")) {
            search.getSortBy().putAll((Map) searchQueryMap.get("sort_by"));
        }

        if (searchQueryMap.containsKey("offset")) {
            if (searchQueryMap.get("offset") instanceof Integer) {
                search.setOffset((Integer) searchQueryMap.get("offset"));
            } else {
                search.setOffset(((BigInteger) searchQueryMap.get("offset")).intValue());
            }
        }

        if (searchQueryMap.containsKey("limit")) {
            if (searchQueryMap.get("limit") instanceof Integer) {
                search.setLimit((Integer) searchQueryMap.get("limit"));
            } else {
                search.setLimit(((BigInteger) searchQueryMap.get("limit")).intValue());
            }
        }

        if (search.getLimit() > 10000) {
            search.setLimit(10000);
        }

        if (search.getLimit() + search.getOffset() > 10000) {
            search.setLimit(10000 - search.getOffset());
        }

        if (searchQueryMap.containsKey("groupQuery")) {
            search.getGroupQuery().addAll((Collection) searchQueryMap.get("groupQuery"));
        }

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
}
