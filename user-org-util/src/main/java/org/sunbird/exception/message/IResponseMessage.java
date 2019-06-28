package org.sunbird.exception.message;

/**
 * This interface will hold all the response key and message
 *
 * @author Amit Kumar
 */
public interface IResponseMessage extends IUserResponseMessage, IOrgResponseMessage {

    String UNAUTHORIZED_USER = "UNAUTHORIZED_USER";
    String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    String INVALID_REQUEST_TIMEOUT = "INVALID_REQUEST_TIMEOUT";
    String INVALID_REQUESTED_DATA = "INVALID_REQUESTED_DATA";
    String INVALID_OPERATION_NAME = "INVALID_OPERATION_NAME";
    String INTERNAL_ERROR = "INTERNAL_ERROR";
    String SERVER_ERROR = "SERVER_ERROR";
    String OPERATION_TIMEOUT = "PROCESS_EXE_TIMEOUT";

}
