package org.sunbird.dao;

/**
 * @author Amit Kumar
 */
public enum DaoImplType {

    OPEN_SABER("openSaber");

    private String type;

    DaoImplType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
