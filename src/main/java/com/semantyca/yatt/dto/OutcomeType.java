package com.semantyca.yatt.dto;

public enum OutcomeType {
    //operation result payload
    UNKNOWN, SAVING_RESULT, DELETE_RESULT,
    //entity payload
    VIEW_PAGE, PLAIN_PAGE, DOCUMENT,
    //message payload
    HARD_ERROR, SOFT_ERROR, WARNING;


}
