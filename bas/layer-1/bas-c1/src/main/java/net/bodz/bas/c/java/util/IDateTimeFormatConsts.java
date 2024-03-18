package net.bodz.bas.c.java.util;

import java.time.format.DateTimeFormatter;

import net.bodz.bas.meta.decl.ThreadUnsafe;

@ThreadUnsafe
public interface IDateTimeFormatConsts {

    DateTimeFormatter ISO_LOCAL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
    DateTimeFormatter ISO_LOCAL_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter ISO_LOCAL_TIME = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS");
    DateTimeFormatter ISO_OFFSET_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ");
    DateTimeFormatter ISO_OFFSET_TIME = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSSZ");
    DateTimeFormatter ISO_ZONED_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ'['z']'");

    DateTimeFormatter UI_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter UI_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter UI_TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter UI_OFFSET_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
    DateTimeFormatter UI_OFFSET_TIME = DateTimeFormatter.ofPattern("HH:mm:ssZ");
    DateTimeFormatter UI_ZONED_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ'['z']'");

// DateTimeFormatter SYS_DATE = DateTimeFormatter.getDateInstance();
// DateTimeFormatter SYS_TIME = DateTimeFormatter.getTimeInstance();
// DateTimeFormatter SYS_DATETIME = DateTimeFormatter.getDateTimeInstance();

//    DateTimeFormatter RFC1123 = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz");
//
//    /**
//     * <code>Sat, 13 Mar 2010 11:29:05 -0800</code>
//     * <p>
//     * Please keep in mind that the [day-of-week ","] is optional in RFC-2822.
//     */
//    DateTimeFormatter RFC2822 = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z");
//
//    DateTimeFormatter RFC822_w2 = DateTimeFormatter.ofPattern("EEE, d MMM yy HH:mm z");
//    DateTimeFormatter RFC822_w2s = DateTimeFormatter.ofPattern("EEE, d MMM yy HH:mm:ss z");
//    DateTimeFormatter RFC822_w4 = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm z");
//    DateTimeFormatter RFC822_w4s = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss z");
//    DateTimeFormatter RFC822_2 = DateTimeFormatter.ofPattern("d MMM yy HH:mm z");
//    DateTimeFormatter RFC822_2s = DateTimeFormatter.ofPattern("d MMM yy HH:mm:ss z");
//    DateTimeFormatter RFC822_4 = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm z");
//    DateTimeFormatter RFC822_4s = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss z");
//    DateTimeFormatter RFC822 = RFC822_w4s;
//    DateTimeFormatter[] RFC822_ALL = { RFC822_w2, RFC822_w2s, RFC822_w4, RFC822_w4s, RFC822_2, RFC822_2s, RFC822_4,
//            RFC822_4s, };
//
    DateTimeFormatter ISO8601 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS ZZZ");
//
//    /** <code>1980-12-31</code> */
//    DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    /** <code>1980-12</code> */
//    DateTimeFormatter YYYY_MM = DateTimeFormatter.ofPattern("yyyy-MM");
//    /** <code>1980-1</code> */
//    DateTimeFormatter YYYY_M = DateTimeFormatter.ofPattern("yyyy-M");
//    /** <code>80-12-31</code> */
//    DateTimeFormatter YY_MM_DD = DateTimeFormatter.ofPattern("yy-MM-dd");
//    /** <code>12/31/1980</code> */
//    DateTimeFormatter MM_DD_YYYY = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//    /** <code>12/31/80</code> */
//    DateTimeFormatter MM_DD_YY = DateTimeFormatter.ofPattern("MM/dd/yy");
//    /** <code>19801231</code> */
//    DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");
//    /** <code>801231</code> */
//    DateTimeFormatter YYMMDD = DateTimeFormatter.ofPattern("yyMMdd");
//
//    /** <code>17:30:59</code> */
//    DateTimeFormatter HH_MM_SS = DateTimeFormatter.ofPattern("HH:mm:ss");
//    /** <code>173059</code> */
//    DateTimeFormatter HHMMSS = DateTimeFormatter.ofPattern("HHmmss");
//
//    /** <code>1980-12-31 17:30:59</code> */
//    DateTimeFormatter D10T8 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    /** <code>17:30:59 12/31/1980</code> */
//    DateTimeFormatter T8D10 = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/yyyy");
//    /** <code>19801231-173059</code> */
//    DateTimeFormatter D8T6 = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
//    /** <code>801231-173059</code> */
//    DateTimeFormatter D6T6 = DateTimeFormatter.ofPattern("yyMMdd-HHmmss");

    DateTimeFormatter JAVASCRIPT = DateTimeFormatter.ofPattern("EE MMM d y H:m:s 'GMT'Z (zz)");

}
