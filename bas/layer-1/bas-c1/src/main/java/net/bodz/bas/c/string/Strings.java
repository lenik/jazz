package net.bodz.bas.c.string;

import java.util.Arrays;

public class Strings {

    public static String repeat(int count, String pattern) {
        StringBuilder buf = new StringBuilder(pattern.length() * count);
        while (--count >= 0)
            buf.append(pattern);
        return buf.toString();
    }

    public static String repeat(int count, char c) {
        char[] buf = new char[count];
        Arrays.fill(buf, c);
        return new String(buf);
    }

    public static String reverse(String s) {
        StringBuilder buf = new StringBuilder(s);
        buf.reverse();
        return buf.toString();
    }

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

    public static String _lcfirst(String s) {
        if (s == null)
            return null;
        if (s.length() <= 1)
            return s.toLowerCase();
        char lcfirst = Character.toLowerCase(s.charAt(0));
        return lcfirst + s.substring(1);
    }

    public static String lcfirst(String s) {
        if (s == null)
            return null;
        switch (s.length()) {
            case 0:
                return s;

            case 1:
                return s.toLowerCase();
        }

        char lcfirst = Character.toLowerCase(s.charAt(0));
        return lcfirst + s.substring(1);
    }

    public static String ucfirst(String s) {
        if (s == null)
            return null;
        if (s.length() <= 1)
            return s.toUpperCase();
        char ucfirst = Character.toUpperCase(s.charAt(0));
        return ucfirst + s.substring(1);
    }

    public static String ucfirstWords(String s) {
        if (s == null)
            return null;
        boolean boundary = true;
        int len = s.length();
        StringBuilder buf = new StringBuilder(len);
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

    public static String ellipsisNull = null;

    /**
     * @return {@link #ellipsisNull} if <code>s</code> is <code>null</code>.
     */
    public static String ellipsis(String s, int len, String ellipse) {
        if (s == null)
            return null;
        if (s.length() <= len)
            return s;
        int elen = ellipse.length();
        if (len > elen)
            return s.substring(0, len - elen) + ellipse;
        else
            return ellipse.substring(0, len);
    }

    /**
     * Returns <code>"head..."</code> if given <code>s</code> is too long.
     *
     * @return {@link #ellipsisNull} if <code>s</code> is <code>null</code>.
     */
    public static String ellipsis(String s, int len) {
        return ellipsis(s, len, "...");
    }

    /**
     * @return {@link #ellipsisNull} if <code>s</code> is <code>null</code>.
     */
    public static String ellipsis(String s, int len, String ellipse, String epstart, String epend) {
        if (s == null)
            return ellipsisNull;
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
            return prefix + ellipsis(s.substring(estart, eend), len - trims, ellipse) + suffix;
        else
            return ellipsis(s, len, ellipse);
    }

    public static int indexOf(String s, char ch, int indexOfOccurence) {
        int begin = 0;
        int end = s.length();
        int index = 0;
        while (begin < end) {
            int pos = s.indexOf(ch, begin);
            if (pos == -1)
                return -1;
            if (index++ == indexOfOccurence)
                return pos;
            begin = pos + 1;
        }
        return -1;
    }

    public static PosRange selectToken(String s, int tokenIndex) {
        return selectToken(s, tokenIndex, '/');
    }

    /**
     * @return null if tokenIndex is out of bounds.
     */
    public static PosRange selectToken(String s, int tokenIndex, char delim) {
        return selectToken(s, tokenIndex, delim, false);
    }

    /**
     * @throws IndexOutOfBoundsException
     *             if tokenIndex is out of bounds and raiseError is true.
     */
    public static PosRange selectToken(String s, int tokenIndex, char delim, boolean raiseError) {
        if (s == null)
            throw new NullPointerException("s");
        if (tokenIndex < 0)
            if (raiseError)
                throw new IndexOutOfBoundsException(String.valueOf(tokenIndex));
            else
                return null;

        int currentIndex = 0;
        int begin = 0;
        int len = s.length();

        while (true) {
            if (begin > len)
                if (raiseError)
                    throw new IndexOutOfBoundsException(String.valueOf(tokenIndex));
                else
                    return null;

            int end = s.indexOf(delim, begin);
            if (end == -1)
                end = s.length();

            if (currentIndex == tokenIndex)
                return new PosRange(begin, end);

            begin = end + 1;
            currentIndex++;
        }
    }

    public static String leadingSpace(String s) {
        int end = s.length();
        while (end > 0) {
            char ch = s.charAt(end - 1);
            if (ch == '\n' || ch == '\r')
                end--;
            else
                break;
        }
        for (int i = 0; i < end; i++) {
            char ch = s.charAt(i);
            if (!Character.isWhitespace(ch))
                return s.substring(0, i);
        }
        return s.substring(0, end);
    }

    public static String trailingSpace(String s) {
        int end = s.length();
        while (end > 0) {
            char ch = s.charAt(end - 1);
            if (ch == '\n' || ch == '\r')
                end--;
            else
                break;
        }
        for (int i = end - 1; i >= 0; i++) {
            char ch = s.charAt(i);
            if (!Character.isWhitespace(ch))
                return s.substring(i + 1, end);
        }
        return s.substring(0, end);
    }

}