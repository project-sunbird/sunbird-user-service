package org.sunbird.user;


import org.sunbird.SearchDtoMapper;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actor.core.BaseActor;
import org.sunbird.common.factory.EsClientFactory;
import org.sunbird.dto.SearchDTO;
import org.sunbird.es.service.ElasticSearchService;
import org.sunbird.helper.ElasticSearchHelper;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import scala.concurrent.Future;

import java.math.BigInteger;
import java.util.*;

/**
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"searchUser"},
        dispatcher = "search-read-dispatcher",
        asyncTasks = {}

)
public class UserSearchActor extends BaseActor {

    static ElasticSearchService es= EsClientFactory.getInstance("rest");

    @Override
    public void onReceive(Request request) throws Throwable {
        if(request.getOperation().equalsIgnoreCase("searchUser")){
            searchUser(request);
        } else {
            onReceiveUnsupportedMessage("UserSearchActor");
        }
    }

    public void  searchUser(Request request) {
        SearchDTO searchDTO= SearchDtoMapper.createSearchDto(request.getRequest());
        Future<Map<String, Object>> future = es.search(searchDTO, "user");
        Map<String, Object> responseMap = (Map) ElasticSearchHelper.getResponseFromFuture(future);
        Response response=new Response();
        response.getResult().put("response",responseMap);
        sender().tell(response,self());
    }

}
