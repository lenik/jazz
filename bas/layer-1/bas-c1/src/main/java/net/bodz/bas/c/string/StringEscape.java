package net.bodz.bas.c.string;

import org.apache.commons.text.StringEscapeUtils;

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
     * @return <code>null</code> for <code>null</code>
     */
    public static String escapeJava(String s) {
        return StringEscapeUtils.escapeJava(s);
    }

    /**
     * @return <code>null</code> for <code>null</code>
     */
    public static String unescapeJava(String s) {
        return StringEscapeUtils.unescapeJava(s);
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
        if (s == null)
            return null;
        s = s.trim();
        if (s.length() < 2)
            throw new ParseException("not quoted");
        char start = s.charAt(0);
        if (start != '\"' && start != '\'')
            throw new ParseException("invalid quote char");
        if (start != s.charAt(s.length() - 1))
            throw new ParseException("unbalanced quote char");
        s = s.substring(1, s.length() - 1);
        return StringEscapeUtils.unescapeJava(s);
    }

}
