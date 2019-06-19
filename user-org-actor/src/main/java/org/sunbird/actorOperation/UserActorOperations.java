package org.sunbird.actorOperation;

public enum UserActorOperations {

    CREATE_USER("createUser");

    private String operation;

    UserActorOperations(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return this.operation;
    }
}
