package org.sunbird.helper;


/**
 * these are the enums defined for the index value of elastic search
 */
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
