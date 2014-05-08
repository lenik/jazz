package net.bodz.bas.c.string;

public class StringPart {

    public static String chop(String s, int chopLen) {
        assert s != null;
        if (s.length() < chopLen)
            return "";
        return s.substring(0, s.length() - chopLen);
    }

    public static String chop(String s) {
        return chop(s, 1);
    }

    public static String chomp(String s, String pattern) {
        assert s != null;
        assert pattern != null;
        int chars = pattern.length();
        if (s.length() < chars)
            return s;
        int len = s.length();
        if (s.substring(len - chars).equals(pattern))
            return s.substring(0, len - chars);
        return s;
    }

    public static String chomp(String s, char ch) {
        assert s != null;
        if (!s.isEmpty()) {
            int len = s.length();
            if (s.charAt(len - 1) == ch)
                s = s.substring(0, len - 1);
        }
        return s;
    }

    public static String chomp(String s) {
        s = chomp(s, '\n');
        s = chomp(s, '\r');
        return s;
    }

    public static String trimLeft(String s, int end) {
        if (end > s.length())
            end = s.length();
        int l = -1;
        while (++l < end)
            if (!Character.isWhitespace(s.charAt(l)))
                break;
        return s.substring(l, end);
    }

    public static String trimLeft(String s) {
        return trimLeft(s, s.length());
    }

    public static String trimRight(String s, int start) {
        if (start < 0)
            start = 0;
        int l = s.length();
        while (--l >= start)
            if (!Character.isWhitespace(s.charAt(l)))
                break;
        return s.substring(start, l + 1);
    }

    public static String trimRight(String s) {
        return trimRight(s, 0);
    }

    public static String ltrim(String s, String prefix) {
        if (s.startsWith(prefix))
            s = s.substring(prefix.length());
        return s;
    }

    public static String rtrim(String s, String suffix) {
        if (s.endsWith(suffix))
            s = s.substring(0, s.length() - suffix.length());
        return s;
    }

    /**
     * @return <code>null</code> if <code>literalPattern</code> isn't found
     */
    public static String before(String s, String literalPattern) {
        return before(s, literalPattern, null);
    }

    public static String before(String s, String literalPattern, String fallback) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return fallback;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>literalPattern</code> isn't found
     */
    public static String before(String s, char literalPattern) {
        return before(s, literalPattern, null);
    }

    public static String before(String s, char literalPattern, String fallback) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return fallback;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't found
     */
    public static String before(String s, byte charCategory) {
        return before(s, charCategory, null);
    }

    public static String before(String s, byte charCategory, String fallback) {
        int pos = StringSearch.indexOf(s, charCategory);
        if (pos == -1)
            return fallback;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>literalPattern</code> isn't found
     */
    public static String beforeLast(String s, String literalPattern) {
        return beforeLast(s, literalPattern, null);
    }

    public static String beforeLast(String s, String literalPattern, String fallback) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return fallback;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't found
     */
    public static String beforeLast(String s, char literalPattern) {
        return beforeLast(s, literalPattern, null);
    }

    public static String beforeLast(String s, char literalPattern, String fallback) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return fallback;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't found
     */
    public static String beforeLast(String s, byte charCategory) {
        return beforeLast(s, charCategory, null);
    }

    public static String beforeLast(String s, byte charCategory, String fallback) {
        int pos = StringSearch.lastIndexOf(s, charCategory);
        if (pos == -1)
            return fallback;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't found
     */
    public static String after(String s, String literalPattern) {
        return after(s, literalPattern, null);
    }

    public static String after(String s, String literalPattern, String fallback) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return fallback;
        return s.substring(pos + literalPattern.length());
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't found
     */
    public static String after(String s, char literalPattern) {
        return after(s, literalPattern, null);
    }

    public static String after(String s, char literalPattern, String fallback) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return fallback;
        return s.substring(pos + 1);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't found
     */
    public static String after(String s, byte charCategory) {
        return after(s, charCategory, null);
    }

    public static String after(String s, byte charCategory, String fallback) {
        int pos = StringSearch.indexOf(s, charCategory);
        if (pos == -1)
            return fallback;
        return s.substring(pos + 1);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't found
     */
    public static String afterLast(String s, String literalPattern) {
        return afterLast(s, literalPattern, null);
    }

    public static String afterLast(String s, String literalPattern, String fallback) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return fallback;
        return s.substring(pos + literalPattern.length());
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't found
     */
    public static String afterLast(String s, char literalPattern) {
        return afterLast(s, literalPattern, null);
    }

    public static String afterLast(String s, char literalPattern, String fallback) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return fallback;
        return s.substring(pos + 1);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't found
     */
    public static String afterLast(String s, byte charCategory) {
        return afterLast(s, charCategory, null);
    }

    public static String afterLast(String s, byte charCategory, String fallback) {
        int pos = StringSearch.lastIndexOf(s, charCategory);
        if (pos == -1)
            return fallback;
        return s.substring(pos + 1);
    }

    public static String getHeadPar(String str) {
        String par = str.trim();
        int newline = par.indexOf('\n');
        if (newline != -1)
            par = par.substring(0, newline).trim();
        return par;
    }

    public static String getTailPar(String str) {
        String par = str.trim();
        int newline = par.indexOf('\n');
        if (newline != -1)
            par = par.substring(newline + 1).trim();
        return par;
    }

    public static String peekDecimal(String str) {
        int len = str.length();
        int numEnd = 0;
        if (str.charAt(0) == '-')
            numEnd++;

        int dots = 0;
        L: while (numEnd < len) {
            char ch = str.charAt(numEnd);

            switch (ch) {
            case '.':
                if (++dots != 1)
                    break L;
                else
                    break;

            default:
                if (ch >= '0' && ch <= '9')
                    break;
                else
                    break L;
            }
            numEnd++;
        }

        return str.substring(0, numEnd);
    }

}
