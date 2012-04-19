package net.bodz.bas.c.string;


public class StringQuote {

    public static String q(String s) {
        return "'" + s + "'";
    }

    public static String qq(String s) {
        return '"' + s + '"';
    }

    public static String qJavaEscaped(String s) {
        String java = StringEscape.java(s);
        return q(java);
    }

    public static String qSQLEscaped(String s) {
        String java = StringEscape.sql(s);
        return q(java);
    }

}
