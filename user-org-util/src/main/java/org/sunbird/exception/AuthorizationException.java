package org.sunbird.exception;

public class AuthorizationException {

    public static class Unauthorized extends BaseException
    {
        public Unauthorized(String code, String message, int responseCode) {
            super(code,message,responseCode);
        }
    }
}
