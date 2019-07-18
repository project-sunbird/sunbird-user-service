package org.sunbird.actorOperation;

public enum OrgActorOperations {
  CREATE_ORG("createOrg"),
  SEARCH_ORG("searchOrg"),
  READ_ORG("readOrg");

  private String operation;

  OrgActorOperations(String operation) {
    this.operation = operation;
  }

  public String getOperation() {
    return this.operation;
  }
}
