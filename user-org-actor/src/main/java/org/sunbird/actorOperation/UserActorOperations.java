package org.sunbird.actorOperation;

public enum UserActorOperations {
  CREATE_USER("createUser"),
  SEARCH_USER("searchUser"),
  READ_USER_BY_ID("readUserById"),
  SAVE_USER_ATTRIBUTES("saveUserAttributes"),
  CREATE_ADDRESS("createUserAddress"),
  SAVE_ES_USER("saveESUser"),
  USER_ES_CREATE("userESCreate"),
  USER_ES_UPDATE("userESUpdate");

  private String operation;

  UserActorOperations(String operation) {
    this.operation = operation;
  }

  public String getOperation() {
    return this.operation;
  }
}
