package net.bodz.bas.c.java.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Dates
        implements IDateFormatConsts {

    public static DateFormat format(String dateFormatString) {
        return new SimpleDateFormat(dateFormatString);
    }

}
