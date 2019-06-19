package org.sunbird.exception.userexception;

import org.sunbird.exception.BaseException;

public class UserException {

    public static class UserNotFound extends BaseException
    {

        public UserNotFound(String code, String message, int responseCode) {
            super(code,message,responseCode);
        }
    }
}
