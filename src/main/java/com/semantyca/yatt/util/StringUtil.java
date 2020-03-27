package com.semantyca.yatt.util;

import de.svenjacobs.loremipsum.LoremIpsum;

public class StringUtil {

    public static String getRndArticle(int maxWords) {
        LoremIpsum loremIpsum = new LoremIpsum();
        return loremIpsum.getWords(NumberUtil.getRandomNumber(1, maxWords));
    }

    public static String getRndParagraph(int maxPragraphs) {
        LoremIpsum loremIpsum = new LoremIpsum();
        return loremIpsum.getParagraphs(NumberUtil.getRandomNumber(1, maxPragraphs));
    }
}
