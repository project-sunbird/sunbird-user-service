package org.sunbird.exception;

public class ESException {

  public static class SaveException extends BaseException {
    public SaveException(String code, String message, int responseCode) {
      super(code, message, responseCode);
    }
  }
}
