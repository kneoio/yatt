package com.semantyca.yatt.model.constant;

public enum StatusType {
    UNKNOWN(0, "unknown"),
    DRAFT(1, "draft"),
    IN_PROGRESS(2, "in progress"),
    DONE(3, "done"),
    SUSPEND(4, "suspend");

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
