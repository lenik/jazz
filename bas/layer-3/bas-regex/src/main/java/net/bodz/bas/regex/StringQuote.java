package net.bodz.bas.regex;


public class StringQuote {

    public static String q(String s) {
        return "'" + s + "'";
    }

    public static String qq(String s) {
        return '"' + s + '"';
    }

    private static PatternProcessor escapeProcessor;
    static {
        escapeProcessor = new PatternProcessor("[\\\\\"\'\r\n]") {
            @Override
            protected void matched(String part) {
                assert part.length() == 1;
                char c = part.charAt(0);
                print(escape(c));
            }
        };
    }

    /**
     * Escape literal character in C/Java language.
     */
    public static String escape(int c) {
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
     * Escape literal string in C/Java language.
     */
    public static String escape(String s) {
        if (s == null)
            return s;
        return escapeProcessor.process(s);
    }

}
