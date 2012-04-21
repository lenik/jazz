package net.bodz.bas.c.java.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Dates {

    public static final DateFormat sysDateFormat;
    public static final DateFormat sysTimeFormat;
    public static final DateFormat sysDateTimeFormat;

    /** 1980-12-31 */
    public static final DateFormat YYYY_MM_DD;
    /** 80-12-31 */
    public static final DateFormat YY_MM_DD;
    /** 12/31/1980 */
    public static final DateFormat MM_DD_YYYY;
    /** 12/31/80 */
    public static final DateFormat MM_DD_YY;
    /** 19801231 */
    public static final DateFormat YYYYMMDD;
    /** 801231 */
    public static final DateFormat YYMMDD;

    /** 17:30:59 */
    public static final DateFormat HH_MM_SS;
    /** 173059 */
    public static final DateFormat HHMMSS;

    /** 1980-12-31 17:30:59 */
    public static final DateFormat D10T8;
    /** 17:30:59 12/31/1980 */
    public static final DateFormat T8D10;
    /** 19801231-173059 */
    public static final DateFormat D8T6;
    /** 801231-173059 */
    public static final DateFormat D6T6;

    static {
        sysDateFormat = DateFormat.getDateInstance();
        sysTimeFormat = DateFormat.getTimeInstance();
        sysDateTimeFormat = DateFormat.getDateTimeInstance();

        YYYY_MM_DD = getFormat("yyyy-MM-dd");
        YY_MM_DD = getFormat("yy-MM-dd");
        MM_DD_YYYY = getFormat("MM/dd/yyyy");
        MM_DD_YY = getFormat("MM/dd/yy");
        YYYYMMDD = getFormat("yyyyMMdd");
        YYMMDD = getFormat("yyMMdd");

        HH_MM_SS = getFormat("hh:mm:ss");
        HHMMSS = getFormat("hhmmss");

        D10T8 = getFormat("yyyy-MM-dd hh:mm:ss");
        T8D10 = getFormat("hh:mm:ss MM/dd/yyyy");
        D8T6 = getFormat("yyyyMMdd-hhmmss");
        D6T6 = getFormat("yyMMdd-hhmmss");
    }

    public static DateFormat getFormat(String dateFormatString) {
        return new SimpleDateFormat(dateFormatString);
    }

}
