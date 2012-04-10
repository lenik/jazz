package net.bodz.mda.xjdoc.meta;

public class WordTokenizer {

    public static String firstWord(String string) {
        int len = string.length();
        int n1; // the first non-space char pos.
        int s2; // the second space char pos.

        for (n1 = 0; n1 < len; n1++) {
            char ch = string.charAt(n1);
            if (!Character.isWhitespace(ch))
                break;
        }

        for (s2 = n1; s2 < len; s2++) {
            char ch = string.charAt(s2);
            if (Character.isWhitespace(ch))
                break;
        }

        return string.substring(0, s2);
    }

}
