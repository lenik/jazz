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

    public static String chomp(String s) {
        return chomp(s, "\n");
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

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String before(String s, String literalPattern) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String before(String s, char literalPattern) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't contained
     */
    public static String before(String s, byte charCategory) {
        int pos = StringSearch.indexOf(s, charCategory);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String beforeLast(String s, String literalPattern) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String beforeLast(String s, char literalPattern) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't contained
     */
    public static String beforeLast(String s, byte charCategory) {
        int pos = StringSearch.lastIndexOf(s, charCategory);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String after(String s, String literalPattern) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(pos + literalPattern.length());
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String after(String s, char literalPattern) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(pos + 1);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't contained
     */
    public static String after(String s, byte charCategory) {
        int pos = StringSearch.indexOf(s, charCategory);
        if (pos == -1)
            return null;
        return s.substring(pos + 1);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String afterLast(String s, String literalPattern) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(pos + literalPattern.length());
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String afterLast(String s, char literalPattern) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(pos + 1);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't contained
     */
    public static String afterLast(String s, byte charCategory) {
        int pos = StringSearch.lastIndexOf(s, charCategory);
        if (pos == -1)
            return null;
        return s.substring(pos + 1);
    }

}
