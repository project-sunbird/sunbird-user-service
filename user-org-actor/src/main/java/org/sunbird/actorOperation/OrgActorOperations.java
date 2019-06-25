package org.sunbird.actorOperation;

public enum OrgActorOperations {

    CREATE_ORG("createOrg");

    private String operation;

    OrgActorOperations(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return this.operation;
    }
}
