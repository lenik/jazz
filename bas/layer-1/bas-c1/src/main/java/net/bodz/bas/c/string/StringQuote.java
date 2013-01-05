package net.bodz.bas.c.string;

public class StringQuote {

    public static String q(String s) {
        return "'" + s + "'";
    }

    public static String qq(String s) {
        return '"' + s + '"';
    }

    public static String qJavaEscaped(String s) {
        String java = StringEscape.escapeJava(s);
        return q(java);
    }

    public static String qqJavaEscaped(String s) {
        String java = StringEscape.escapeJava(s);
        return qq(java);
    }

    public static String qSQLEscaped(String s) {
        String java = StringEscape.escapeSql(s);
        return q(java);
    }

}
