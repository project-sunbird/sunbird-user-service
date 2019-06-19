package org.sunbird.exception;


import org.apache.commons.lang3.StringUtils;

public class BaseException extends Exception {

    /** serialVersionUID. */
    private static final long serialVersionUID = 1L;
    /** code String code ResponseCode. */
    private String code;
    /** message String ResponseCode. */
    private String message;
    /** responseCode int ResponseCode. */
    private int responseCode;

    /**
     * This code is for client to identify the error and based on that do the message localization.
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * To set the client code.
     *
     * @param code String
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * message for client in english.
     *
     * @return String
     */
    @Override
    public String getMessage() {
        return message;
    }

    /** @param message String */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method will provide response code, this code will be used in response header.
     *
     * @return int
     */
    public int getResponseCode() {
        return responseCode;
    }

    /** @param responseCode int */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * three argument constructor.
     *
     * @param code String
     * @param message String
     * @param responseCode int
     */
    public BaseException(String code, String message, int responseCode) {
        super();
        this.code = code;
        this.message = message;
        this.responseCode = responseCode;
    }

    public static void throwUnauthorizedErrorException() throws BaseException {
        throw new BaseException(
                ResponseCode.unAuthorized.getErrorCode(),
                ResponseCode.unAuthorized.getErrorMessage(),
                ResponseCode.UNAUTHORIZED.getResponseCode());
    }

    public static void throwResourceNotFoundException() throws BaseException {
        throw new BaseException(
                ResponseCode.resourceNotFound.getErrorCode(),
                ResponseCode.resourceNotFound.getErrorMessage(),
                ResponseCode.RESOURCE_NOT_FOUND.getResponseCode());
    }

    public static void throwClientErrorException(ResponseCode responseCode) throws BaseException {
        throw new BaseException(
                responseCode.getErrorCode(),responseCode.getErrorMessage(),
                ResponseCode.CLIENT_ERROR.getResponseCode());
    }

    public static void throwClientErrorException(ResponseCode responseCode, String exceptionMessage) throws BaseException {
        throw new BaseException(
                responseCode.getErrorCode(),
                StringUtils.isBlank(exceptionMessage) ? responseCode.getErrorMessage() : exceptionMessage,
                ResponseCode.CLIENT_ERROR.getResponseCode());
    }

    public static void throwServerErrorException(ResponseCode responseCode) throws BaseException {
        throw new BaseException(
                responseCode.getErrorCode(), responseCode.getErrorMessage() ,
                ResponseCode.SERVER_ERROR.getResponseCode());
    }

    public static void throwServerErrorException(ResponseCode responseCode, String exceptionMessage) throws BaseException {
        throw new BaseException(
                responseCode.getErrorCode(),
                StringUtils.isBlank(exceptionMessage) ? responseCode.getErrorMessage() : exceptionMessage,
                ResponseCode.SERVER_ERROR.getResponseCode());
    }

}
