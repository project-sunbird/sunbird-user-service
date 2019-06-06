package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.sunbird.common.models.util.ProjectLogger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import java.io.IOException;
import java.util.Map;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.route;


/**
 * This a helper class for All the Controllers Test
 * @author anmolgupta
 */
public class TestHelper extends WithApplication {


    /**
     * This method will perform a request call.
     * @param url
     * @param method
     * @param requestMap
     * @param headerMap
     * @return Result
     */
    public Result performTest(String url, String method, Map requestMap, Map headerMap) {
        String data = mapToJson(requestMap);
        Http.RequestBuilder req;
        if (StringUtils.isNotBlank(data) && !requestMap.isEmpty()) {
            JsonNode json = Json.parse(data);
            req = new Http.RequestBuilder().bodyJson(json).uri(url).method(method);
        } else {
            req = new Http.RequestBuilder().uri(url).method(method);
        }
        req.headers(headerMap);
        Result result = route(fakeApplication(), req);
        return result;
    }

    /**
     * This method is responsible for converting map to json
     * @param map
     * @return String
     */

    public String mapToJson(Map map) {
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonResp = "";

        if (map != null) {
            try {
                jsonResp = mapperObj.writeValueAsString(map);
            } catch (IOException e) {
                ProjectLogger.log(e.getMessage(), e);
            }
        }
        return jsonResp;
    }

    /**
     * This method is used to return the status Code for the perform request
     * @param result
     * @return
     */

    public int getResponseStatus(Result result) {
        return result.status();
    }
}
