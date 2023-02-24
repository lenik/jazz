package net.bodz.bas.text.unicode;

/**
 * @see https://en.wikipedia.org/wiki/Macron_below
 */
public class UnicodeMacrons {

    public static final char SPACING_LETTER = '\u02CD';
    public static final char SPACING_LOW_LINE = '\u005F';
    public static final char SPACING_DOUBLE_LOW_LINE = '\u2017';

    public static final char COMBINING_MODIFIER_LETTER_MINUS_SIGN = '\u02D7';
    public static final char COMBINING_MINUS_SIGN_BELOW = '\u0320';
    public static final char COMBINING_SINGLE = '\u0331';
    public static final char COMBINING_LOW_LINE = '\u0332';
    public static final char COMBINING_DOUBLE_LOW_LINE = '\u0333';
    public static final char COMBINING_EQUALS_SIGN_BELOW = '\u0347';
    public static final char COMBINING_DOUBLE = '\u035F';
    public static final char COMBINING_MACRON_LEFT_HALF_BELOW = '\uFE2B';
    public static final char COMBINING_MACRON_RIGHT_HALF_BELOW = '\uFE2C';
    public static final char COMBINING_CONJONING_MACRON_BELOW = '\uFE2D';

    public static String lowLine(String s) {
        return interleaveAfter(s, COMBINING_LOW_LINE);
    }

    public static String interleaveBefore(String s, char mod) {
        if (s == null)
            return s;
        int len = s.length();
        StringBuilder out = new StringBuilder(len * 2);
        interleaveBefore(s, mod);
        return out.toString();
    }

    public static void interleaveBefore(StringBuilder out, String s, char mod) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            out.append(mod);
            out.append(ch);
        }
    }

    public static String interleaveAfter(String s, char mod) {
        if (s == null)
            return s;
        int len = s.length();
        StringBuilder out = new StringBuilder(len * 2);
        interleaveAfter(s, mod);
        return out.toString();
    }

    public static void interleaveAfter(StringBuilder out, String s, char mod) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            out.append(ch);
            out.append(mod);
        }
    }

}
