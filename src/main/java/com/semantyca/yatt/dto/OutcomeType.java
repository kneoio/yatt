package com.semantyca.yatt.dto;

public enum OutcomeType {
    //default
    UNKNOWN,
    //entity payload
    VIEW_PAGE, PLAIN_PAGE, DOCUMENT,
    //process response payload
    INFO, VALIDATION_ERROR, HARD_ERROR, SOFT_ERROR, WARNING;


}
