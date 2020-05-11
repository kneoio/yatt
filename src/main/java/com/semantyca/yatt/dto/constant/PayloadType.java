package com.semantyca.yatt.dto.constant;

public enum PayloadType {
    ACTIONS("actions");

    private String alias;

    PayloadType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

}
