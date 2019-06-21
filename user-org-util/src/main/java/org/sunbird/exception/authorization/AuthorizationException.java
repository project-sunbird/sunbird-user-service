package org.sunbird.exception.authorization;

import org.sunbird.exception.BaseException;

public class AuthorizationException {

    public static class Unauthorized extends BaseException
    {
        public Unauthorized(String code, String message, int responseCode) {
            super(code,message,responseCode);
        }
    }
}
