package net.bodz.bas.c.string;

public class StringQuote {

    public static final char singleQuoteChar = '\'';
    public static final char doubleQuoteChar = '\"';
    public static final String singleQuote = "\'";
    public static final String doubleQuote = "\"";
    static final String javaNullLiteral = "null";

    public static String q(String s) {
        return singleQuoteChar + s + singleQuoteChar;
    }

    public static String qq(String s) {
        return doubleQuoteChar + s + doubleQuoteChar;
    }

    public static String q(Object s) {
        return q(s == null ? null : s.toString());
    }

    public static String qq(Object s) {
        return qq(s == null ? null : s.toString());
    }

    public static String qJavaChar(char ch) {
        String java = StringEscape.escapeJava(ch);
        return q(java);
    }

    public static String qJavaString(String s) {
        if (s == null)
            return null;
        String java = StringEscape.escapeJava(s);
        return q(java);
    }

    public static String qqJavaString(String s) {
        if (s == null)
            return javaNullLiteral;
        String java = StringEscape.escapeJava(s);
        return qq(java);
    }

    public static String qXmlAttr(String s) {
        if (s == null)
            return null;
        String xmlAttr = StringEscape.escapeXmlAttr(s);
        return q(xmlAttr);
    }

    public static String qqXmlAttr(String s) {
        if (s == null)
            return null;
        String xmlAttr = StringEscape.escapeXmlAttr(s);
        return qq(xmlAttr);
    }

    public static String qDoubleQ(String s) {
        String dblq = StringEscape.doubleQ(s);
        return q(dblq);
    }

    public static String qqDoubleQQ(String s) {
        String dblqq = StringEscape.doubleQQ(s);
        return qq(dblqq);
    }

}
