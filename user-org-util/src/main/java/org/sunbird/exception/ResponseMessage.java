package org.sunbird.exception;

/**
 * This interface will hold all the response key and message
 *
 * @author Manzarul
 */
public interface ResponseMessage {

  interface Message {
    String UNAUTHORIZED_USER = "You are not authorized.";
    String RESOURCE_NOT_FOUND = "Requested resource not found";
    String INVALID_REQUEST_TIMEOUT = "Invalid request timeout value {0}.";
    String INVALID_REQUESTED_DATA = "Requested data for this operation is not valid.";
    String INVALID_OPERATION_NAME =
            "Operation name is invalid. Please provide a valid operation name";
    String INTERNAL_ERROR = "Process failed,please try again later.";
    String OPERATION_TIMEOUT = "Request processing taking too long time. Please try again later.";

  }

  interface Key {
    String UNAUTHORIZED_USER = "UNAUTHORIZED_USER";
    String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    String INVALID_REQUEST_TIMEOUT = "INVALID_REQUEST_TIMEOUT";
    String INVALID_REQUESTED_DATA = "INVALID_REQUESTED_DATA";
    String INVALID_OPERATION_NAME = "INVALID_OPERATION_NAME";
    String INTERNAL_ERROR = "INTERNAL_ERROR";
    String OPERATION_TIMEOUT = "PROCESS_EXE_TIMEOUT";
  }
}
