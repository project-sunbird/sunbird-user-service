package org.sunbird.user;

import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actor.core.BaseActor;
import org.sunbird.common.factory.EsClientFactory;
import org.sunbird.es.service.ElasticSearchService;
import org.sunbird.helper.ElasticSearchHelper;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import scala.concurrent.Future;

import java.util.Map;


/**
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"readUserById"},
        dispatcher = "search-read-dispatcher",
        asyncTasks = {}

)
public class UserReadActor extends BaseActor {

    static ElasticSearchService es = EsClientFactory.getInstance("rest");

    @Override
    public void onReceive(Request request) throws Throwable {
        if (request.getOperation().equalsIgnoreCase("readUserById")) {
            readUserById(request);
        } else {
            onReceiveUnsupportedMessage("UserCreateActor");
        }
    }

    public void readUserById(Request request) {
        String userId = (String) request.getRequest().get("userId");
        Future<Map<String, Object>> future = es.getDataByIdentifier("user", userId);
        Map<String, Object> responseMap = (Map) ElasticSearchHelper.getResponseFromFuture(future);
        Response response = new Response();
        response.put("response", responseMap);
        sender().tell(response, self());
    }

}
