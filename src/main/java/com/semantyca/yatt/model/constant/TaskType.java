package com.semantyca.yatt.model.constant;

public enum TaskType {
    UNKNOWN(0, "unknown");

    private int code;
    private String alias;

    TaskType(int code, String lang) {
        this.code = code;
        this.alias = lang;
    }

    public static TaskType getType(int code) {
        for (TaskType type : values()) {
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
