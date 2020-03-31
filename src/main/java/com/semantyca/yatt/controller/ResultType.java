package com.semantyca.yatt.controller;

public enum ResultType {
    UNKNOWN(0, "unknown"),SUCCESS(100, "success");

    private int code;
    private String alias;

    ResultType(int code, String lang) {
        this.code = code;
        this.alias = lang;
    }

}
