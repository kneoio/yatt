package com.semantyca.yatt.util;

import de.svenjacobs.loremipsum.LoremIpsum;

import java.util.Random;

public class StringUtil {

    public static String getRndText() {
        return genRndText("qwertyuiopasdfghjklzxcvbnm", 10);
    }

    public static String getRndText(int len) {
        return genRndText("qwertyuiopasdfghjklzxcvbnm", len);
    }

    public static String getRndColor() {
        Random rand = new Random();
        int myRandomNumber = rand.nextInt(0x10) + 0x10;
        return Integer.toHexString(myRandomNumber);
    }

    public static String genRndText(String setOfTheLetters, int len) {
        Random r = new Random();
        String key = "";
        char[] letters = new char[setOfTheLetters.length() + 10];

        for (int i = 0; i < 10; i++) {
            letters[i] = Character.forDigit(i, 10);
        }

        for (int i = 0; i < setOfTheLetters.length(); i++) {
            letters[i + 10] = setOfTheLetters.charAt(i);
        }

        for (int i = 0; i < len; i++) {
            key += letters[Math.abs(r.nextInt()) % letters.length];
        }

        return key;
    }

    public static String getRndArticle(int maxWords) {
        LoremIpsum loremIpsum = new LoremIpsum();
        return loremIpsum.getWords(NumberUtil.getRandomNumber(1, maxWords));
    }

    public static String getRndParagraph(int maxPragraphs) {
        LoremIpsum loremIpsum = new LoremIpsum();
        return loremIpsum.getParagraphs(NumberUtil.getRandomNumber(1, maxPragraphs));
    }
}
