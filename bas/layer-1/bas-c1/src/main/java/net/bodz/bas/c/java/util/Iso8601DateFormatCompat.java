package net.bodz.bas.c.java.util;

import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Iso8601DateFormatCompat
        extends SimpleDateFormat {

    private static final long serialVersionUID = 1L;

    static final String form1 = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    static final String form2 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public Iso8601DateFormatCompat() {
        super(form2);
    }

    public Iso8601DateFormatCompat(Locale locale) {
        super(form2, locale);
    }

    public Iso8601DateFormatCompat(DateFormatSymbols formatSymbols) {
        super(form2, formatSymbols);
    }

    @Override
    public Date parse(String text, ParsePosition pos) {
        int n = text.length();
        if (text.endsWith("Z")) {
            return super.parse(text.substring(0, n - 1) + "+0000", pos);
        }
        if (n > 3 && text.charAt(n - 3) == ':') {
            text = text.substring(0, n - 3) + text.substring(n - 2);
        }
        return super.parse(text, pos);
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
        StringBuffer rfcFormat = super.format(date, toAppendTo, pos);
        int n = rfcFormat.length();
        if (rfcFormat.substring(n - 5).equals("+0000")) {
            return rfcFormat.replace(n - 5, n, "Z");
        }
        return rfcFormat.insert(n - 2, ":");
    }

}
