package org.sunbird.user;

import akka.dispatch.Futures;
import akka.dispatch.Mapper;
import akka.pattern.Patterns;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.sunbird.BaseActor;
import org.sunbird.actor.core.ActorConfig;
import org.sunbird.exception.BaseException;
import org.sunbird.request.Request;
import org.sunbird.response.Response;
import org.sunbird.util.jsonkey.JsonKey;
import scala.concurrent.Future;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this actor class is used to process the user attributes or entity
 *
 * @author Amit Kumar
 */

@ActorConfig(
        tasks = {"saveUserAttributes"},
        dispatcher = "user-dispatcher",
        asyncTasks = {}
)
public class UserAttributeProcessorActor extends BaseActor {
    @Override
    public void onReceive(Request request) throws Throwable {
        saveUserAttributes(request);
    }

    private void saveUserAttributes(Request request) {
        Map<String, Object> userMap = request.getRequest();
        List<Future<Object>> futures = getFutures(userMap);
        Future<Iterable<Object>> futuresSequence = Futures.sequence(futures, getContext().dispatcher());
        Future<Response> consolidatedFutureResponse = getConsolidatedFutureResponse(futuresSequence);
        Patterns.pipe(consolidatedFutureResponse, getContext().dispatcher()).to(sender());
    }

    @SuppressWarnings("unchecked")
    private List<Future<Object>> getFutures(Map<String, Object> userMap) {
        List<Future<Object>> futures = new ArrayList<>();

        if (userMap.containsKey(JsonKey.ADDRESS)
                && CollectionUtils.isNotEmpty((List<Map<String, Object>>) userMap.get(JsonKey.ADDRESS))) {
            futures.add(saveAddress(userMap));
        }

        if (userMap.containsKey(JsonKey.EDUCATION)
                && CollectionUtils.isNotEmpty((List<Map<String, Object>>) userMap.get(JsonKey.EDUCATION))) {
            futures.add(saveEducation(userMap));
        }

        if (userMap.containsKey(JsonKey.JOB_PROFILE)
                && CollectionUtils.isNotEmpty(
                (List<Map<String, Object>>) userMap.get(JsonKey.JOB_PROFILE))) {
            futures.add(saveJobProfile(userMap));
        }

        if (CollectionUtils.isNotEmpty((List<Map<String, String>>) userMap.get(JsonKey.EXTERNAL_IDS))) {
            futures.add(saveUserExternalIds(userMap));
        }

        if (StringUtils.isNotBlank((String) userMap.get(JsonKey.ORGANISATION_ID))
                || StringUtils.isNotBlank((String) userMap.get(JsonKey.ROOT_ORG_ID))) {
            futures.add(saveUserOrgDetails(userMap));
        }

        return futures;
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
        return null;
    }

}