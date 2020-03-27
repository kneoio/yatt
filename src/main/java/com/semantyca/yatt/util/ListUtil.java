package com.semantyca.yatt.util;

import java.util.List;
import java.util.Random;

public class ListUtil {

    public static Object getRndListElement(List<?> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }

}
