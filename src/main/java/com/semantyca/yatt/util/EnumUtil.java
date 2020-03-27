package com.semantyca.yatt.util;

import java.util.Random;

public class EnumUtil {

    public static <T> T getRndElement(T enumData[]) {
        Random rnd = new Random();
        return enumData[rnd.nextInt(enumData.length)];
    }
}
