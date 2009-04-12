package net.bodz.bas.types.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @test DatesTest
 */
public class Dates {

    public static final DateFormat sysDateFormat;
    public static final DateFormat sysTimeFormat;
    public static final DateFormat sysDateTimeFormat;

    /** 1980-12-31 */
    public static final DateFormat YYYY_MM_DD;
    /** 12/31/1980 */
    public static final DateFormat MM_DD_YYYY;
    /** 19801231 */
    public static final DateFormat YYYYMMDD;
    /** 801231 */
    public static final DateFormat YYMMDD;

    /** 17:30:59 */
    public static final DateFormat HH_MM_SS;
    /** 173059 */
    public static final DateFormat HHMMSS;

    static {
        sysDateFormat = DateFormat.getDateInstance();
        sysTimeFormat = DateFormat.getTimeInstance();
        sysDateTimeFormat = DateFormat.getDateTimeInstance();

        YYYY_MM_DD = getFormat("yyyy-MM-dd"); //$NON-NLS-1$
        MM_DD_YYYY = getFormat("MM/dd/yyyy"); //$NON-NLS-1$
        YYYYMMDD = getFormat("yyyyMMdd"); //$NON-NLS-1$
        YYMMDD = getFormat("yyMMdd"); //$NON-NLS-1$

        HH_MM_SS = getFormat("hh:mm:ss"); //$NON-NLS-1$
        HHMMSS = getFormat("hhmmss"); //$NON-NLS-1$
    }

    public static DateFormat       dateFormat;
    public static DateFormat       timeFormat;
    public static DateFormat       dateTimeFormat;
    static {
        dateFormat = YYYY_MM_DD;
        timeFormat = HH_MM_SS;
        dateTimeFormat = getFormat("yyyy-MM-dd hh:mm:ss"); //$NON-NLS-1$
    }

    public static DateFormat getFormat(String dateFormatString) {
        return new SimpleDateFormat(dateFormatString);
    }

}
