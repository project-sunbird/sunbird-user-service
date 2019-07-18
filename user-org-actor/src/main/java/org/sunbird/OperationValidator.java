package org.sunbird;

/** this is a Util class for actors. */
public class OperationValidator {

  /**
   * this method is used to compare the operation names for actor
   *
   * @param x
   * @param y
   * @return
   */
  public static boolean matchOperationName(String x, String y) {
    return x.equalsIgnoreCase(y);
  }
}
