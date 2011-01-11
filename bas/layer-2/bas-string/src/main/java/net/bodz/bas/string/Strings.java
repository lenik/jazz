package net.bodz.bas.text.util;

import java.util.Arrays;

public class Strings {

    static final char PADCHAR = ' ';

    public static String padLeft(String s, int len, char padChar) {
        int padLen = len - s.length();
        if (padLen <= 0)
            return s;
        return Strings.repeat(padLen, padChar) + s;
    }

    public static String padLeft(String s, int len) {
        return padLeft(s, len, PADCHAR);
    }

    public static String padRight(String s, int len, char padChar) {
        int padLen = len - s.length();
        if (padLen <= 0)
            return s;
        return s + Strings.repeat(padLen, padChar);
    }

    public static String padRight(String s, int len) {
        return padRight(s, len, PADCHAR);
    }

    public static String padMiddle(String s, int len, char padChar) {
        int padLen = len - s.length();
        if (padLen <= 0)
            return s;
        int padLeft = padLen / 2; // slightly left-ward
        int padRight = padLen - padLeft;
        return Strings.repeat(padLeft, padChar) + s + Strings.repeat(padRight, padChar);
    }

    public static String padMiddle(String s, int len) {
        return padMiddle(s, len, PADCHAR);
    }

    public static String ucfirst(String s) {
        if (s == null)
            return null;
        if (s.length() <= 1)
            return s.toUpperCase();
        char ucfirst = Character.toUpperCase(s.charAt(0));
        return ucfirst + s.substring(1);
    }

    public static String lcfirst(String s) {
        if (s == null)
            return null;
        if (s.length() <= 1)
            return s.toUpperCase();
        char lcfirst = Character.toLowerCase(s.charAt(0));
        return lcfirst + s.substring(1);
    }

    public static String ucfirstWords(String s) {
        boolean boundary = true;
        int len = s.length();
        StringBuffer buf = new StringBuffer(len);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            boolean isLetter = Character.isLetter(c);
            if (boundary && isLetter)
                c = Character.toUpperCase(c);
            boundary = !isLetter;
            buf.append(c);
        }
        return buf.toString();
    }

    /**
     * helloWorld => hello-world
     */
    public static String hyphenatize(String words) {
        while (words.startsWith("_"))
            words = words.substring(1);
        StringBuffer buf = new StringBuffer(words.length() * 3 / 2);
        boolean breakNext = false;
        for (int wordStart = 0; wordStart < words.length();) {
            int wordEnd;
            wordEnd = StringSearch.indexOf(words, Character.UPPERCASE_LETTER, wordStart + 1);
            if (wordEnd == -1)
                wordEnd = words.length();
            String word = words.substring(wordStart, wordEnd);
            if (breakNext)
                buf.append('-');
            buf.append(word);
            breakNext = word.length() > 1;
            wordStart = wordEnd;
        }
        String s = buf.toString();
        if (s.startsWith("-"))
            s = s.substring(1);
        return s.toLowerCase();
    }

    /**
     * hello-world => helloWorld
     */
    public static String dehyphenatize(String hstr) {
        String[] parts = hstr.split("-");
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.isEmpty())
                buf.append('_');
            else {
                if (i != 0)
                    part = Strings.ucfirst(part);
                buf.append(part);
            }
        }
        return buf.toString();
    }

    public static String repeat(int count, String pattern) {
        StringBuffer buf = new StringBuffer(pattern.length() * count);
        while (--count >= 0)
            buf.append(pattern);
        return buf.toString();
    }

    public static String repeat(int count, char c) {
        char[] buf = new char[count];
        Arrays.fill(buf, c);
        return new String(buf);
    }

    public static String ellipse(String s, int len, String ellipse) {
        if (s.length() <= len)
            return s;
        int elen = ellipse.length();
        if (len > elen)
            return s.substring(0, len - elen) + ellipse;
        else
            return ellipse.substring(0, len);
    }

    public static String ellipse(String s, int len) {
        return ellipse(s, len, "...");
    }

    public static String ellipse(String s, int len, String ellipse, String epstart, String epend) {
        int estart = 0;
        if (epstart != null)
            if ((estart = s.indexOf(epstart)) == -1)
                estart = 0;
            else
                estart += epstart.length();
        int eend = s.length();
        if (epend != null)
            if ((eend = s.lastIndexOf(epend)) == -1)
                eend = s.length();
        int trims = estart + (s.length() - eend);
        String prefix = s.substring(0, estart);
        String suffix = s.substring(eend);
        if (len > trims + ellipse.length())
            return prefix + ellipse(s.substring(estart, eend), len - trims, ellipse) + suffix;
        else
            return ellipse(s, len, ellipse);
    }

}
