package org.sunbird.exception;

public class ProjectCommonException {

    public static class ServerError extends BaseException
    {

        public ServerError(String code, String message, int responseCode) {
            super(code,message,responseCode);
        }
    }

    public static class ClientError extends BaseException
    {

        public ClientError(String code, String message, int responseCode) {
            super(code,message,responseCode);
        }
    }
}
