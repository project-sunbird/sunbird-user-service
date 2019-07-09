package controllers.logsmanager.validator;

import org.apache.commons.lang3.EnumUtils;
import org.sunbird.request.Request;
import org.sunbird.util.LoggerEnum;
import org.sunbird.util.ProjectLogger;
import org.sunbird.util.jsonkey.JsonKey;

public class LogValidator {

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
   * This method will validate wheather required requestBody logLevel is present or not
   *
   * @param request
   * @return boolean
   */
  public static boolean isLogParamsPresent(Request request) {
    return (request != null && request.get(JsonKey.LOG_LEVEL) != null) ? true : false;
  }

  /**
   * This method will check wheather the Log Param provided is supported or not
   *
   * @param level
   * @return boolean
   */
  public static boolean isValidLogLevelPresent(String level) {
    return validateLogLevels(level) ? true : false;
  }
}
