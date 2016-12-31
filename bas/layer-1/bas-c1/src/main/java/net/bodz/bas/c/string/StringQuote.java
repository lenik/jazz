package net.bodz.bas.c.string;

public class StringQuote {

    public static String q(String s) {
        return "\'" + s + "\'";
    }

    public static String qq(String s) {
        return '\"' + s + '\"';
    }

    public static String q(Object s) {
        return q(s == null ? null : s.toString());
    }

    public static String qq(Object s) {
        return qq(s == null ? null : s.toString());
    }

    public static String qJavaString(String s) {
        if (s == null)
            return null;
        String java = StringEscape.escapeJava(s);
        return q(java);
    }

    public static String qqJavaString(String s) {
        if (s == null)
            return "null";
        String java = StringEscape.escapeJava(s);
        return qq(java);
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
