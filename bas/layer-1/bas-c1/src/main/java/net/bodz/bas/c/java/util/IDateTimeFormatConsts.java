package net.bodz.bas.c.java.util;

import java.time.format.DateTimeFormatter;

import net.bodz.bas.meta.decl.ThreadUnsafe;

@ThreadUnsafe
public interface IDateTimeFormatConsts {

    DateTimeFormatter ISO_LOCAL_DATE_TIME = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    DateTimeFormatter ISO_LOCAL_DATE = DateTimeFormatter.ISO_LOCAL_DATE;
    DateTimeFormatter ISO_LOCAL_TIME = DateTimeFormatter.ISO_LOCAL_TIME;

    DateTimeFormatter ISO_OFFSET_DATE_TIME =
            // DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSSSSSzzz");
            DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    DateTimeFormatter ISO_OFFSET_TIME =
            // DateTimeFormatter.ofPattern( "HH:mm:ss.SSSSSSzzz");
            DateTimeFormatter.ISO_OFFSET_TIME;

    DateTimeFormatter ISO_ZONED_DATE_TIME =
            // DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSSSSSzzz'['VV']'");
            DateTimeFormatter.ISO_ZONED_DATE_TIME;

    DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_DATE;
    DateTimeFormatter ISO_TIME = DateTimeFormatter.ISO_TIME;
    DateTimeFormatter ISO_DATE_TIME = DateTimeFormatter.ISO_DATE_TIME;
    DateTimeFormatter ISO_ORDINAL_DATE = DateTimeFormatter.ISO_ORDINAL_DATE;
    DateTimeFormatter ISO_WEEK_DATE = DateTimeFormatter.ISO_WEEK_DATE;
    DateTimeFormatter ISO_INSTANT = DateTimeFormatter.ISO_INSTANT;
    DateTimeFormatter BASIC_ISO_DATE = DateTimeFormatter.BASIC_ISO_DATE;
    DateTimeFormatter RFC_1123_DATE_TIME = DateTimeFormatter.RFC_1123_DATE_TIME;

    DateTimeFormatter UI_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter UI_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter UI_TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter UI_OFFSET_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:sszzz");
    DateTimeFormatter UI_OFFSET_TIME = DateTimeFormatter.ofPattern("HH:mm:ssZ");
    DateTimeFormatter UI_ZONED_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:sszzz'['VV']'");

// DateTimeFormatter SYS_DATE = DateTimeFormatter.getDateInstance();
// DateTimeFormatter SYS_TIME = DateTimeFormatter.getTimeInstance();
// DateTimeFormatter SYS_DATETIME = DateTimeFormatter.getDateTimeInstance();

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
    DateTimeFormatter ISO8601 = ISO_DATE_TIME;

    DateTimeFormatter JAVASCRIPT = DateTimeFormatter.ofPattern("EE MMM d y H:m:s 'GMT'Z (zz)");

}
