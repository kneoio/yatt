package com.semantyca.yatt.model.constant;

public enum PriorityType {
    UNKNOWN(0, "unknown"),LOW(11, "low"), MIDDLE(12, "middle"), HIGH(13, "high") , URGENT(14, "urgent");


    private int code;
    private String alias;

    PriorityType(int code, String lang) {
        this.code = code;
        this.alias = lang;
    }

    public static PriorityType getType(int code) {
        for (PriorityType type : values()) {
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
