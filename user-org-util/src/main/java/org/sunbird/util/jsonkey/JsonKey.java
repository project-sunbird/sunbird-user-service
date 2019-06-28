package org.sunbird.util.jsonkey;

/**
 * This class will contains all the key related to request and response.
 *
 * @author Manzarul
 */
public interface JsonKey extends UserJsonKey{

  String CLASSNAME = "class";
  String DATA = "data";
  String EKS = "eks";
  String ID = "id";
  String LEVEL = "level";
  String MESSAGE = "message";
  String METHODNAME = "method";
  String PDATA = "pdata";
  String REQUEST_MESSAGE_ID = "msgId";
  String STACKTRACE = "stacktrace";
  String VER = "ver";
  String OK = "ok";
  String SUNBIRD_USER_ORG_LOG_LEVEL = "sunbird_user_org_log_level";
  String LOG_LEVEL = "logLevel";
  String ERROR = "error";
  String EMPTY_STRING = "";
  String UNAUTHORIZED = "Unauthorized";

  String PDATA_ID = "telemetry_pdata_id";
  String PDATA_PID = "telemetry_pdata_pid";
  String PDATA_VERSION = "telemetry_pdata_ver";

}
