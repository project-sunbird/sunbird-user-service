package org.sunbird.helper;

public enum EsIndex {

    USER("user");
    private String name;

    EsIndex(String name) {
        this.name = name;

    }

    public String getIndexName() {
        return this.name;
    }
}
