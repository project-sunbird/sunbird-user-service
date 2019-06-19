package org.sunbird.exception.actorservice;

import org.sunbird.exception.BaseException;

public class ActorServiceException {

    public static class InvalidOperationName extends BaseException
    {
        public InvalidOperationName(String code, String message, int responseCode) {
            super(code,message,responseCode);
        }
    }


}
