package org.sunbird;

/**
 * @author Amit Kumar
 */
public enum DaoImplType {

    OS("openSaber"),
    ES("elasticsearch");

    private String type;

    DaoImplType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
