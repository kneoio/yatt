package com.semantyca.juka.exception;

import com.semantyca.yatt.util.StringUtil;

public class ExceptionUtil {

    public static String getErrorCode(String errorMnemonic) {
        return errorMnemonic +  "#" + StringUtil.getRndText(20);
    }

}
