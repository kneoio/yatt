package com.semantyca.yatt.model.constant;

public enum StatusType {
    UNKNOWN(0, "unknown"),ON_TIME(11, "on_time"), DELAYING(12, "delaying"), STOPPED(13, "stopped"); ;

    private int code;
    private String alias;

    StatusType(int code, String lang) {
        this.code = code;
        this.alias = lang;
    }

    public static StatusType getType(int code) {
        for (StatusType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public int getCode() {
        return code;
    }

    public String getAlias() {
        return alias;
    }
}
