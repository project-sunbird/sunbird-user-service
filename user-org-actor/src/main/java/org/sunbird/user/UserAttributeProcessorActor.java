package org.sunbird.user;

import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.pattern.Patterns;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.actorOperation.UserActorOperations;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.jsonkey.JsonKey;
import scala.concurrent.Future;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this actor class is used to process the user attributes or entity (Address, Education, Job Profile, User Org Relation,
 * User External Ids)
 *
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"saveUserAttributes"},
        dispatcher = "user-dispatcher",
        asyncTasks = {}
)
public class UserAttributeProcessorActor extends BaseActor {

    List<Future<Object>> futures = new ArrayList<>();

    @Override
    public void onReceive(Request request) {
        saveUserAttributes(request);
    }

    private void saveUserAttributes(Request request) {
        Map<String, Object> userMap = request.getRequest();
        List<Future<Object>> futures = getFutures(userMap);
        Future<Iterable<Object>> futuresSequence = Futures.sequence(futures, getContext().dispatcher());
        Future<Response> consolidatedFutureResponse = getConsolidatedFutureResponse(futuresSequence);
        Patterns.pipe(consolidatedFutureResponse, getContext().dispatcher()).to(sender());
    }

    private List<Future<Object>> getFutures(Map<String, Object> userMap) {
        addFuture(saveAddress(userMap));
        addFuture(saveEducation(userMap));
        addFuture(saveJobProfile(userMap));
        addFuture(saveUserExternalIds(userMap));
        addFuture(saveUserOrgDetails(userMap));
        return futures;
    }

    private void addFuture(Future<Object> future){
        if(null != future){
            futures.add(future);
        }
    }

    private Future<Response> getConsolidatedFutureResponse(Future<Iterable<Object>> futuresSequence) {
        return futuresSequence.map(
                new Mapper<Iterable<Object>, Response>() {
                    Map<String, Object> map = new HashMap<>();
                    List<Object> errorList = new ArrayList<>();

                    @Override
                    public Response apply(Iterable<Object> futureResult) {
                        for (Object object : futureResult) {
                            if (object instanceof Response) {
                                Response response = (Response) object;
                                Map<String, Object> result = response.getResult();
                                String key = (String) result.get(JsonKey.KEY);
                                if (StringUtils.isNotBlank(key)) {
                                    map.put(key, result.get(key));
                                }
                                @SuppressWarnings("unchecked")
                                List<String> errMsgList = (List<String>) result.get(JsonKey.ERROR_MSG);
                                if (CollectionUtils.isNotEmpty(errMsgList)) {
                                    for (String err : errMsgList) {
                                        Map<String, Object> map = new HashMap<>();
                                        map.put(JsonKey.ATTRIBUTE, key);
                                        map.put(JsonKey.MESSAGE, err);
                                        errorList.add(map);
                                    }
                                }
                            } else if (object instanceof BaseException) {
                                errorList.add(((BaseException) object).getMessage());
                            } else if (object instanceof Exception) {
                                errorList.add(((Exception) object).getMessage());
                            }
                        }
                        map.put(JsonKey.ERRORS, errorList);
                        Response response = new Response();
                        response.put(JsonKey.RESPONSE, map);
                        return response;
                    }
                },
                getContext().dispatcher());
    }

    private Future<Object> saveUserOrgDetails(Map<String, Object> userMap) {
        if (StringUtils.isNotBlank((String) userMap.get(JsonKey.ORGANISATION_ID))
                || StringUtils.isNotBlank((String) userMap.get(JsonKey.ROOT_ORG_ID))) {
            return null;
        }
        return null;
    }

    private Future<Object> saveUserExternalIds(Map<String, Object> userMap) {
        return null;
    }

    private Future<Object> saveJobProfile(Map<String, Object> userMap) {
        return null;
    }

    private Future<Object> saveEducation(Map<String, Object> userMap) {
        return null;
    }

    private Future<Object> saveAddress(Map<String, Object> userMap) {
        if (CollectionUtils.isNotEmpty((List<Map<String, Object>>) userMap.get(JsonKey.ADDRESS))) {
            Map<String, Object> entity = new HashMap<>();
            entity.put(JsonKey.ADDRESS, userMap.get(JsonKey.ADDRESS));
            entity.put(JsonKey.USER_ID, userMap.get(JsonKey.USER_ID));
            return saveEntity(entity, UserActorOperations.CREATE_ADDRESS.getOperation());
        }
        return null;
    }

    private Future<Object> saveEntity(Map<String, Object> entity, String actorOperation) {
        try {
            Request request = new Request();
            request.getRequest().putAll(entity);
            request.setOperation(actorOperation);
            return Patterns.ask(getActorRef(actorOperation), request, t);
        } catch (Exception ex) {
            ProjectLogger.log(
                    "UserAttributeProcessorActor:saveEntity: Exception occurred with error message = "
                            + ex.getMessage(),
                    ex);
        }
        return null;
    }
}
