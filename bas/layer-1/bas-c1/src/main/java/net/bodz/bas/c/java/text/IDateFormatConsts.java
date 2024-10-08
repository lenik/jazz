package net.bodz.bas.c.java.text;

import java.text.DateFormat;

import net.bodz.bas.meta.decl.ThreadUnsafe;

@ThreadUnsafe
public interface IDateFormatConsts {

//    DateFormat INSTANT = Dates.format("HH:mm:ss");
    DateFormat ISO_LOCAL_DATE_TIME = Dates.format("yyyy-MM-dd'T'HH:mm:ss");
    DateFormat ISO_LOCAL_DATE = Dates.format("yyyy-MM-dd");
    DateFormat ISO_LOCAL_TIME = Dates.format("HH:mm:ss");
//    DateFormat ISO_OFFSET_DATE_TIME = Dates.format("yyyy-MM-dd'T'HH:mm:ssZ");
//    DateFormat ISO_OFFSET_TIME = Dates.format("HH:mm:ssZ");
//    DateFormat ISO_ZONED_DATE_TIME = Dates.format("yyyy-MM-dd'T'HH:mm:ssZ[z]");

    DateFormat UI_DATE_TIME = Dates.format("yyyy-MM-dd HH:mm:ss");
    DateFormat UI_DATE = Dates.format("yyyy-MM-dd");
    DateFormat UI_TIME = Dates.format("HH:mm:ss");
    DateFormat UI_OFFSET_DATE_TIME = Dates.format("yyyy-MM-dd HH:mm:ssZ");
    DateFormat UI_OFFSET_TIME = Dates.format("HH:mm:ssZ");
    DateFormat UI_ZONED_DATE_TIME = Dates.format("yyyy-MM-dd HH:mm:ssZ[z]");

//    DateFormat SYS_DATE = DateFormat.getDateInstance();
//    DateFormat SYS_TIME = DateFormat.getTimeInstance();
//    DateFormat SYS_DATETIME = DateFormat.getDateTimeInstance();
//
    DateFormat RFC1123 = Dates.format("EEE, dd MMM yyyy HH:mm:ss zzz");
//
//    /**
//     * <code>Sat, 13 Mar 2010 11:29:05 -0800</code>
//     * <p>
//     * Please keep in mind that the [day-of-week ","] is optional in RFC-2822.
//     */
//    DateFormat RFC2822 = Dates.format("EEE, dd MMM yyyy HH:mm:ss Z");
//
//    DateFormat RFC822_w2 = Dates.format("EEE, d MMM yy HH:mm z");
//    DateFormat RFC822_w2s = Dates.format("EEE, d MMM yy HH:mm:ss z");
//    DateFormat RFC822_w4 = Dates.format("EEE, d MMM yyyy HH:mm z");
//    DateFormat RFC822_w4s = Dates.format("EEE, d MMM yyyy HH:mm:ss z");
//    DateFormat RFC822_2 = Dates.format("d MMM yy HH:mm z");
//    DateFormat RFC822_2s = Dates.format("d MMM yy HH:mm:ss z");
//    DateFormat RFC822_4 = Dates.format("d MMM yyyy HH:mm z");
//    DateFormat RFC822_4s = Dates.format("d MMM yyyy HH:mm:ss z");
//    DateFormat RFC822 = RFC822_w4s;
//    DateFormat[] RFC822_ALL = { RFC822_w2, RFC822_w2s, RFC822_w4, RFC822_w4s, RFC822_2, RFC822_2s, RFC822_4, RFC822_4s, };
//
    DateFormat ISO8601Z = Dates.format("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    DateFormat ISO8601compat = new Iso8601DateFormatCompat();
    DateFormat ISO8601 = _helper.format("yyyy-MM-dd'T'HH:mm:ss.SSSX", ISO8601compat);

//    /** <code>1980-12-31</code> */
//    DateFormat YYYY_MM_DD = Dates.format("yyyy-MM-dd");
    /** <code>1980-12</code> */
    DateFormat YYYY_MM = Dates.format("yyyy-MM");
//    /** <code>1980-1</code> */
//    DateFormat YYYY_M = Dates.format("yyyy-M");
//    /** <code>80-12-31</code> */
//    DateFormat YY_MM_DD = Dates.format("yy-MM-dd");
    /** <code>12/31/1980</code> */
    DateFormat MM_DD_YYYY = Dates.format("MM/dd/yyyy");
//    /** <code>12/31/80</code> */
//    DateFormat MM_DD_YY = Dates.format("MM/dd/yy");
    /** <code>19801231</code> */
    DateFormat YYYYMMDD = Dates.format("yyyyMMdd");
//    /** <code>801231</code> */
//    DateFormat YYMMDD = Dates.format("yyMMdd");
//
    /** <code>17:30:59.567</code> */
    DateFormat HH_MM_SS_MS = Dates.format("hh:mm:ss.SSS");
//    /** <code>17:30:59</code> */
//    DateFormat HH_MM_SS = Dates.format("hh:mm:ss");
//    /** <code>173059</code> */
//    DateFormat HHMMSS = Dates.format("hhmmss");
//
//    /** <code>1980-12-31 17:30:59</code> */
//    DateFormat D10T8 = Dates.format("yyyy-MM-dd hh:mm:ss");
//    /** <code>17:30:59 12/31/1980</code> */
//    DateFormat T8D10 = Dates.format("hh:mm:ss MM/dd/yyyy");
    /** <code>19801231-173059</code> */
    DateFormat D8T6 = Dates.format("yyyyMMdd-hhmmss");
//    /** <code>801231-173059</code> */
//    DateFormat D6T6 = Dates.format("yyMMdd-hhmmss");

    DateFormat JAVASCRIPT = Dates.format("EE MMM d y H:m:s 'GMT'Z (zz)");

}

class _helper {

    static DateFormat format(String fmt, DateFormat fallback) {
        try {
            return Dates.format(fmt);
        } catch (IllegalArgumentException e) {
            return fallback;
        }
    }

}