package org.sunbird.exception;

public class ValidationException {

  public static class BadRequest extends BaseException {

    public BadRequest(String code, String message, int responseCode) {
      super(code, message, responseCode);
    }
  }
}
