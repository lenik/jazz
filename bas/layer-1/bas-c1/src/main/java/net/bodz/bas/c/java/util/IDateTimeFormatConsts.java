package net.bodz.bas.c.java.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import net.bodz.bas.meta.decl.ThreadUnsafe;

@ThreadUnsafe
public interface IDateTimeFormatConsts {

// DateTimeFormatter SYS_DATE = DateTimeFormatter.getDateInstance();
// DateTimeFormatter SYS_TIME = DateTimeFormatter.getTimeInstance();
// DateTimeFormatter SYS_DATETIME = DateTimeFormatter.getDateTimeInstance();

    DateTimeFormatter RFC1123 = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss zzz");

    /**
     * <code>Sat, 13 Mar 2010 11:29:05 -0800</code>
     * <p>
     * Please keep in mind that the [day-of-week ","] is optional in RFC-2822.
     */
    DateTimeFormatter RFC2822 = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z");

    DateTimeFormatter RFC822_w2 = DateTimeFormat.forPattern("EEE, d MMM yy HH:mm z");
    DateTimeFormatter RFC822_w2s = DateTimeFormat.forPattern("EEE, d MMM yy HH:mm:ss z");
    DateTimeFormatter RFC822_w4 = DateTimeFormat.forPattern("EEE, d MMM yyyy HH:mm z");
    DateTimeFormatter RFC822_w4s = DateTimeFormat.forPattern("EEE, d MMM yyyy HH:mm:ss z");
    DateTimeFormatter RFC822_2 = DateTimeFormat.forPattern("d MMM yy HH:mm z");
    DateTimeFormatter RFC822_2s = DateTimeFormat.forPattern("d MMM yy HH:mm:ss z");
    DateTimeFormatter RFC822_4 = DateTimeFormat.forPattern("d MMM yyyy HH:mm z");
    DateTimeFormatter RFC822_4s = DateTimeFormat.forPattern("d MMM yyyy HH:mm:ss z");
    DateTimeFormatter RFC822 = RFC822_w4s;
    DateTimeFormatter[] RFC822_ALL = { RFC822_w2, RFC822_w2s, RFC822_w4, RFC822_w4s, RFC822_2, RFC822_2s, RFC822_4,
            RFC822_4s, };

    DateTimeFormatter ISO8601 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS ZZZ");

    /** <code>1980-12-31</code> */
    DateTimeFormatter YYYY_MM_DD = DateTimeFormat.forPattern("yyyy-MM-dd");
    /** <code>1980-12</code> */
    DateTimeFormatter YYYY_MM = DateTimeFormat.forPattern("yyyy-MM");
    /** <code>1980-1</code> */
    DateTimeFormatter YYYY_M = DateTimeFormat.forPattern("yyyy-M");
    /** <code>80-12-31</code> */
    DateTimeFormatter YY_MM_DD = DateTimeFormat.forPattern("yy-MM-dd");
    /** <code>12/31/1980</code> */
    DateTimeFormatter MM_DD_YYYY = DateTimeFormat.forPattern("MM/dd/yyyy");
    /** <code>12/31/80</code> */
    DateTimeFormatter MM_DD_YY = DateTimeFormat.forPattern("MM/dd/yy");
    /** <code>19801231</code> */
    DateTimeFormatter YYYYMMDD = DateTimeFormat.forPattern("yyyyMMdd");
    /** <code>801231</code> */
    DateTimeFormatter YYMMDD = DateTimeFormat.forPattern("yyMMdd");

    /** <code>17:30:59</code> */
    DateTimeFormatter HH_MM_SS = DateTimeFormat.forPattern("hh:mm:ss");
    /** <code>173059</code> */
    DateTimeFormatter HHMMSS = DateTimeFormat.forPattern("hhmmss");

    /** <code>1980-12-31 17:30:59</code> */
    DateTimeFormatter D10T8 = DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss");
    /** <code>17:30:59 12/31/1980</code> */
    DateTimeFormatter T8D10 = DateTimeFormat.forPattern("hh:mm:ss MM/dd/yyyy");
    /** <code>19801231-173059</code> */
    DateTimeFormatter D8T6 = DateTimeFormat.forPattern("yyyyMMdd-hhmmss");
    /** <code>801231-173059</code> */
    DateTimeFormatter D6T6 = DateTimeFormat.forPattern("yyMMdd-hhmmss");

    DateTimeFormatter JAVASCRIPT = DateTimeFormat.forPattern("EE MMM d y H:m:s 'GMT'Z (zz)");

}
