package net.bodz.bas.c.java.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates
        implements IDateFormatConsts {

    public static DateFormat format(String dateFormatString) {
        return new SimpleDateFormat(dateFormatString);
    }

    public static Date addDays(Date date, int days) {
        return new Date(date.getTime() + 86400_000L * days);
    }

}
