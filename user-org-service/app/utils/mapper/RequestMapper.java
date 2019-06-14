/** */
package utils.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import play.libs.Json;

/**
 * This class will map the requested json data into custom class.
 *
 * @author Manzarul
 */
public class RequestMapper {

  /**
   * Method to map request
   *
   * @param requestData JsonNode
   * @param obj Class<T>
   * @exception RuntimeException
   * @return <T>
   */
  public static <T> Object mapRequest(JsonNode requestData, Class<T> obj) throws RuntimeException {

    if (requestData == null) return null;

    try {
      return Json.fromJson(requestData, obj);
    } catch (Exception e) {
      ProjectLogger.log("ControllerRequestMapper error : " + e.getMessage(), e);
      ProjectLogger.log(
          "RequestMapper:mapRequest Requested data : " + requestData, LoggerEnum.INFO.name());
      return null;
    }
  }
}
