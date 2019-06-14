package controllers.logsmanager.validator;

import java.util.*;
import org.apache.commons.lang3.EnumUtils;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.UserOrgJsonKey;
import org.sunbird.util.request.Request;

public class LogValidator {

  /**
   * This method will valid the passed level is supported by service or not
   *
   * @param request
   * @return boolean
   */
  public static boolean checkLogValidationError(Request request) throws Exception {
    ProjectLogger.log(
        String.format(
            "%s:%s: request got %s",
            LogValidator.class.getSimpleName(), "validateLogRequest", request));
    List<Enum> enumValues = new ArrayList<Enum>(EnumSet.allOf(LoggerEnum.class));
    if (request != null) {
      if (request.get(UserOrgJsonKey.LOG_LEVEL) != null) {
        if (validateLogLevels((String) request.get(UserOrgJsonKey.LOG_LEVEL))) {
          return true;
        } else {
          throw new Exception("supported Log Levels are " + Arrays.asList(enumValues.toString()));
        }
      } else {
        throw new Exception(String.format("Missing Required Param %s", UserOrgJsonKey.LOG_LEVEL));
      }
    } else {
      throw new Exception(UserOrgJsonKey.MESSAGE + " no request body found");
    }
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
}
