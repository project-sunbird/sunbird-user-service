package org.sunbird.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * This class will used to log the project message in any level.
 *
 * @author Manzarul
 */
public class ProjectLogger {

  private static String eVersion = "1.0";
  private static String pVersion = "1.0";
  private static String dataId = "Sunbird";
  private static ObjectMapper mapper = new ObjectMapper();
  private static Logger rootLogger = (Logger) LogManager.getLogger("defaultLogger");
  static PropertiesCache propertiesCache = PropertiesCache.getInstance();

  /** To log only message. */
  public static void log(String message) {
    log(message, null, LoggerEnum.DEBUG.name());
  }

  public static void log(String message, Throwable e) {
    log(message, null, e);
  }

  public static void log(String message, Throwable e, Map<String, Object> telemetryInfo) {
    log(message, null, e);
  }

  private static String generateStackTrace(StackTraceElement[] elements) {
    StringBuilder builder = new StringBuilder("");
    for (StackTraceElement element : elements) {
      builder.append(element.toString());
    }
    return builder.toString();
  }

  public static void log(String message, String logLevel) {
    log(message, null, logLevel);
  }

  /** To log message, data in used defined log level. */
  public static void log(String message, LoggerEnum logEnum) {
    info(message, null, logEnum);
  }

  /** To log message, data in used defined log level. */
  public static void log(String message, Object data, String logLevel) {
    backendLog(message, data, null, logLevel);
  }

  /** To log exception with message and data. */
  public static void log(String message, Object data, Throwable e) {
    backendLog(message, data, e, LoggerEnum.ERROR.name());
  }

  /** To log exception with message and data for user specific log level. */
  public static void log(String message, Object data, Throwable e, String logLevel) {
    backendLog(message, data, e, logLevel);
  }

  private static void info(String message, Object data) {
    rootLogger.info(getBELogEvent(LoggerEnum.INFO.name(), message, data));
  }

  private static void info(String message, Object data, LoggerEnum loggerEnum) {
    rootLogger.info(getBELogEvent(LoggerEnum.INFO.name(), message, data, loggerEnum));
  }

  private static void debug(String message, Object data) {
    rootLogger.debug(getBELogEvent(LoggerEnum.DEBUG.name(), message, data));
  }

  private static void error(String message, Object data, Throwable exception) {
    rootLogger.error(getBELogEvent(LoggerEnum.ERROR.name(), message, data, exception));
  }

  private static void warn(String message, Object data, Throwable exception) {
    rootLogger.warn(getBELogEvent(LoggerEnum.WARN.name(), message, data, exception));
  }

  private static void backendLog(String message, Object data, Throwable e, String logLevel) {
    if (!StringUtils.isBlank(logLevel)) {

      switch (logLevel) {
        case "INFO":
          info(message, data);
          break;
        case "DEBUG":
          debug(message, data);
          break;
        case "WARN":
          warn(message, data, e);
          break;
        case "ERROR":
          error(message, data, e);
          break;
        default:
          debug(message, data);
          break;
      }
    }
  }

  private static String getBELogEvent(
      String logLevel, String message, Object data, LoggerEnum logEnum) {
    String logData = getBELog(logLevel, message, data, null, logEnum);
    return logData;
  }

  private static String getBELogEvent(String logLevel, String message, Object data) {
    String logData = getBELog(logLevel, message, data, null, null);
    return logData;
  }

  private static String getBELogEvent(String logLevel, String message, Object data, Throwable e) {
    String logData = getBELog(logLevel, message, data, e, null);
    return logData;
  }

  private static String getBELog(
      String logLevel, String message, Object data, Throwable exception, LoggerEnum logEnum) {
    String mid = dataId + "." + System.currentTimeMillis() + "." + UUID.randomUUID();
    long unixTime = System.currentTimeMillis();
    LogEvent te = new LogEvent();
    Map<String, Object> eks = new HashMap<String, Object>();
    eks.put(UserOrgJsonKey.LEVEL, logLevel);
    eks.put(UserOrgJsonKey.MESSAGE, message);
    String msgId = UUID.randomUUID().toString();
    if (null != msgId) {
      eks.put(UserOrgJsonKey.REQUEST_MESSAGE_ID, msgId);
    }
    if (null != data) {
      eks.put(UserOrgJsonKey.DATA, data);
    }
    if (null != exception) {
      eks.put(UserOrgJsonKey.STACKTRACE, ExceptionUtils.getStackTrace(exception));
    }
    if (logEnum != null) {
      te.setEid(logEnum.name());
    } else {
      te.setEid(LoggerEnum.BELOG.name());
    }
    te.setEts(unixTime);
    te.setMid(mid);
    te.setVer(eVersion);
    te.setContext(dataId, pVersion);
    String jsonMessage = null;
    try {
      te.setEdata(eks);
      jsonMessage = mapper.writeValueAsString(te);
    } catch (Exception e) {
      ProjectLogger.log(e.getMessage(), e);
    }
    return jsonMessage;
  }

  /**
   * This method will be called from Application class(default startup class). which will
   * automatically set the Log level by taking its value from system environment file.
   */
  public static void setUserOrgServiceProjectLogger(String level) {

    switch (level.toUpperCase()) {
      case "INFO":
        changeLogLevel(Level.INFO);
        break;
      case "ERROR":
        changeLogLevel(Level.ERROR);
        break;
      case "DEBUG":
        changeLogLevel(Level.DEBUG);
        break;
      case "WARN":
        changeLogLevel(Level.WARN);
        break;
      case "ALL":
        changeLogLevel(Level.ALL);
        break;
      default:
        changeLogLevel(Level.INFO);
    }
  }

  /**
   * This method will be take Log Level as input params and will set the Log Level dynamically.
   * Configurator class is in package org.apache.logging.log4j.core.config.
   *
   * @param level
   */
  public static void changeLogLevel(Level level) {
    Configurator.setLevel(rootLogger.getName(), level);
    Configurator.setRootLevel(Level.INFO);
    log(String.format("Log Level set to :%s", level), level.name());
  }

  /**
   * This method will take the value of Log level from system or env file. if not find it will
   * identify from externalresource.properties file and set the level
   */
  public static void setLogLevel(String level) {
    if (StringUtils.isBlank(level)) {
      level =
          StringUtils.isNotBlank(
                  propertiesCache.readProperty(UserOrgJsonKey.SUNBIRD_USER_ORG_LOG_LEVEL))
              ? propertiesCache.readProperty(UserOrgJsonKey.SUNBIRD_USER_ORG_LOG_LEVEL)
              : Level.INFO.name();
    }
    setUserOrgServiceProjectLogger(level);
  }
}
