package org.sunbird;

public enum ServiceImplType {

    USER("userService");

    private String serviceType;

    ServiceImplType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return this.serviceType;
    }
}
