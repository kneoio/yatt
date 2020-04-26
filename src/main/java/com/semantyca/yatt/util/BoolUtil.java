package com.semantyca.yatt.util;

import java.util.Random;

/**
 * Created by kaira on 11/28/17.
 */
public class BoolUtil {

    public static boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
