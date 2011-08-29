package net.bodz.bas.util.string;

public class StringEscape {

    /**
     * Escape literal character in Java/C style.
     */
    public static String java(int c) {
        switch (c) {
        case '\r':
            return "\\r";
        case '\n':
            return "\\n";
        case '\t':
            return "\\t";
        case '\0':
            return "\\0";

        case '\\': // followings: "\\"+c:
            return "\\\\";
        case '\"':
            return "\\\"";
        case '\'':
            return "\\\'";
        }
        return String.valueOf((char) c);
    }

    /**
     * Escape literal string in Java/C style.
     */
    public static String java(String s) {
        if (s == null)
            return s;
        int len = s.length();
        StringBuilder buf = new StringBuilder(len * 2);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            buf.append(java(c));
        }
        return buf.toString();
    }

    public static String sql(String s) {
        if (s == null)
            return null;
        return s.replace("\'", "\'\'");
    }

}
