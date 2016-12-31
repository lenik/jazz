package net.bodz.bas.c.string;

import net.bodz.bas.err.ParseException;

public class StringEscape {

    static String hex(int n) {
        return Integer.toHexString(n);
    }

    /**
     * Escape literal character in Java/C style.
     */
    public static String escapeJava(int ch) {
        // Character.isLetterOrDigit(ch)
        if (ch <= 0x7f) {
            if (ch < 0x20)
                switch (ch) {
                case '\b':
                    return "\\b";
                case '\n':
                    return "\\n";
                case '\t':
                    return "\\t";
                case '\f':
                    return "\\f";
                case '\r':
                    return "\\r";
                default:
                    if (ch > 0xf)
                        return "\\u00" + hex(ch);
                    else
                        return "\\u000" + hex(ch);
                }

            else
                // assert ch >= 0x20 && ch <= 0x7f;
                switch (ch) {
                case '\'': // OPT escape option
                    return "\\'";
                    // return "'";
                case '"':
                    return "\\\"";
                case '\\':
                    return "\\\\";
                case '/': // OPT escape option
                    return "/";
                }
        } // [0..7f]

        return String.valueOf((char) ch);
    }

    /**
     * Escape literal string in Java/C style.
     * 
     * @see org.apache.commons.lang.StringEscapeUtils#escapeJava(String)
     */
    public static String escapeJava(String s) {
        if (s == null)
            return s;
        int len = s.length();
        StringBuilder buf = new StringBuilder(len * 2);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            buf.append(escapeJava(c));
        }
        return buf.toString();
    }

    /**
     * @see org.apache.commons.lang.StringEscapeUtils#escapeSql(String)
     */
    public static String doubleQ(String s) {
        if (s == null)
            return null;
        return s.replace("\'", "\'\'");
    }

    public static String doubleQQ(String s) {
        if (s == null)
            return null;
        return s.replace("\"", "\"\"");
    }

    public static String parseQuotedJavaString(String s)
            throws ParseException {
        SimpleJavaStringDFA parser = new SimpleJavaStringDFA();
        String string = (String) parser.parse(s);
        return string;
    }

}
