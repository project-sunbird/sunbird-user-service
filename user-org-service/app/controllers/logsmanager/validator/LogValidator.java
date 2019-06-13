package controllers.logsmanager.validator;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.*;
import org.apache.commons.lang3.EnumUtils;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.UserOrgJsonKey;
import org.sunbird.util.mapper.RequestMapper;
import org.sunbird.util.request.Request;

public class LogValidator {
  static Map<String, Object> responseMap = new HashMap<>();

  /**
   * This method will valid the passed level is supported by service or not
   *
   * @param request
   * @return boolean
   */
  protected static boolean validateLogRequest(Request request) {
    ProjectLogger.log(
        String.format(
            "%s:%s: request got %s",
            LogValidator.class.getSimpleName(), "validateLogRequest", request));
    List<Enum> enumValues = new ArrayList<Enum>(EnumSet.allOf(LoggerEnum.class));
    if (request != null && request.get(UserOrgJsonKey.LOG_LEVEL) != null) {
      if (validateLogLevels((String) request.get(UserOrgJsonKey.LOG_LEVEL))) {
        ProjectLogger.setLogLevel((String) request.get(UserOrgJsonKey.LOG_LEVEL));
        return true;
      }
      responseMap.put(UserOrgJsonKey.ERROR, true);
      responseMap.put(
          UserOrgJsonKey.MESSAGE,
          "supported Log Levels are " + Arrays.asList(enumValues.toString()));
      return false;
    }
    responseMap.put("error", true);
    responseMap.put(
        UserOrgJsonKey.MESSAGE,
        String.format("Missing Required Param %s", UserOrgJsonKey.LOG_LEVEL));
    return false;
  }

  /**
   * This method will validate the log levels supported by service or not
   *
   * @param level
   * @return boolean
   */
  protected static boolean validateLogLevels(String level) {
    ProjectLogger.log(
        String.format(
            "%s:%s: level to be set %s",
            LogValidator.class.getSimpleName(), " validateLogLevels", level));
    return EnumUtils.isValidEnum(LoggerEnum.class, level.toUpperCase());
  }

  /**
   * This method responsibility is to process log request and return response to controller.
   *
   * @param jsonNode
   * @return Map
   */
  public static Map<String, Object> setLogLevelEvent(JsonNode jsonNode) {
    ProjectLogger.log(
        String.format(
            "%s:%s:started at %s",
            LogValidator.class.getSimpleName(), " setLogLevelEvent", System.currentTimeMillis()));
    Request request = (Request) RequestMapper.mapRequest(jsonNode, Request.class);
    if (request != null) {
      if (validateLogRequest(request)) {
        responseMap.put("error", false);
        responseMap.put(
            UserOrgJsonKey.MESSAGE,
            String.format(
                "%s set to %s", UserOrgJsonKey.LOG_LEVEL, request.get(UserOrgJsonKey.LOG_LEVEL)));
      } else {

      }
      ProjectLogger.log(
          String.format(
              "%s:%s:ended at %s",
              LogValidator.class.getSimpleName(), " setLogLevelEvent", System.currentTimeMillis()));
    } else {
      responseMap.put("error", true);
      responseMap.put(UserOrgJsonKey.MESSAGE, "no request body found");
    }
    return responseMap;
  }
}
