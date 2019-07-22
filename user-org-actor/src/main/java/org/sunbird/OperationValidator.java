package org.sunbird;

/** this is a Util class for actors. */
public class OperationValidator {

  /**
   * this method is used to compare the operation names for actor
   *
   * @param operation1
   * @param operation2
   * @return boolean
   */
  public static boolean isSame(String operation1, String operation2) {
    return operation1.equalsIgnoreCase(operation2);
  }
}
