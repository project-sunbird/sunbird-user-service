/** */
package utils.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.sunbird.exception.BaseException;
import org.sunbird.exception.Localizer;
import org.sunbird.exception.ResponseCode;
import org.sunbird.exception.ResponseMessage;
import org.sunbird.exception.actorservice.ActorServiceException;
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
  public static <T> Object mapRequest(JsonNode requestData, Class<T> obj) throws BaseException {
    ProjectLogger.log(
        "RequestMapper:mapRequest:Requested data:" + requestData, LoggerEnum.INFO.name());
    if (requestData == null) return null;

    try {
      return Json.fromJson(requestData, obj);
    } catch (Exception e) {
      ProjectLogger.log("RequestMapper error : " + e.getMessage(), e);
      ProjectLogger.log(
          "RequestMapper:mapRequest Requested data : " + requestData, LoggerEnum.INFO.name());
      /**
       * TODO replcae null with locale param in Localizer.getInstance().getMessage method
       */
      throw new ActorServiceException.InvalidRequestData(
              ResponseMessage.INVALID_REQUESTED_DATA,
              Localizer.getInstance().getMessage(ResponseMessage.INVALID_REQUESTED_DATA,null),
              ResponseCode.CLIENT_ERROR.getCode());
     }
    }
  }

