package org.sunbird;

/** this is a Util class for actors. */
public class OperationValidator {

  /**
   * this method is used to compare the operation names for actor
   *
   * @param actorOperation
   * @param requestOperation
   * @return boolean
   */
  public static boolean matchTwoOperations(String actorOperation, String requestOperation) {
    return actorOperation.equalsIgnoreCase(requestOperation);
  }
}
