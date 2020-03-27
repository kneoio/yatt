package com.semantyca.yatt.model.constant;

public enum StageType {
    UNKNOWN(0, "unknown"),DRAFT(1, "draft"),INPROGRESS(2, "in progress"), DONE(3, "done"), SUSPEND(4, "suspend");

    private int code;
    private String alias;

    StageType(int code, String lang) {
        this.code = code;
        this.alias = lang;
    }

    public static StageType getType(int code) {
        for (StageType type : values()) {
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
