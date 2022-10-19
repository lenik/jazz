package net.bodz.bas.t.variant.fn;

import net.bodz.bas.c.string.StringQuote;

public class QuotedStringFn {

    public static String quote(String s) {
        if (s == null)
            return s;
        return StringQuote.qqJavaString(s);
    }

    public static final String DEFAULT_SEPARATOR = " ";

    public static String quote(String... buf) {
        return quote(DEFAULT_SEPARATOR, buf, 0, buf.length);
    }

    public static String quote(String[] buf, int off, int len) {
        return quote(DEFAULT_SEPARATOR, buf, off, len);
    }

    public static String quote(String separator, String[] buf) {
        return quote(separator, buf, 0, buf.length);
    }

    public static String quote(String separator, String[] buf, int off, int len) {
        if (separator == null)
            throw new NullPointerException("separator");
        int cap = 0;
        int nsep = separator.length();
        for (int i = 0; i < len; i++)
            cap += (buf[i] == null ? 4/* null */ : buf[i].length()) + nsep;

        StringBuilder sb = new StringBuilder(cap);
        for (int i = 0; i < len; i++) {
            if (i != 0)
                sb.append(separator);
            String qq = quote(buf[off++]);
            sb.append(qq);
        }
        return sb.toString();
    }

}
