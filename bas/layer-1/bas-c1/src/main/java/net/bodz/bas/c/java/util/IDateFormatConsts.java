package net.bodz.bas.c.java.util;

import java.text.DateFormat;

import net.bodz.bas.meta.decl.ThreadUnsafe;

@ThreadUnsafe
public interface IDateFormatConsts {

    DateFormat SYS_DATE = DateFormat.getDateInstance();
    DateFormat SYS_TIME = DateFormat.getTimeInstance();
    DateFormat SYS_DATETIME = DateFormat.getDateTimeInstance();

    /**
     * <code>2010-01-01T12:00:00+01:00</code>
     * <p>
     * Be aware, the time zone formats available to SimpleDateFormat (Java 6 and earlier) are not
     * ISO 8601 compliant. SimpleDateFormat understands time zone strings like "GMT+01:00" or
     * "+0100", the latter according to RFC # 822.
     * <p>
     * Even if Java 7 added support for time zone descriptors according to ISO 8601,
     * SimpleDateFormat is still not able to properly parse a complete date string, as it has no
     * support for optional parts.
     * <p>
     * The easier solution is possibly to use the data type converter in JAXB, since JAXB must be
     * able to parse ISO8601 date string according to the XML Schema specification.
     * 
     * @see javax.xml.bind.DatatypeConverter#parseDateTime(String)
     */
    DateFormat ISO8601 = Dates.format("yyyy-MM-dd'T'HH:mm:ssXXX");

    /**
     * <code>2001-07-04T12:08:56.235-07:00</code>
     * <p>
     * Be aware, the time zone formats available to SimpleDateFormat (Java 6 and earlier) are not
     * ISO 8601 compliant. SimpleDateFormat understands time zone strings like "GMT+01:00" or
     * "+0100", the latter according to RFC # 822.
     * <p>
     * Even if Java 7 added support for time zone descriptors according to ISO 8601,
     * SimpleDateFormat is still not able to properly parse a complete date string, as it has no
     * support for optional parts.
     * <p>
     * The easier solution is possibly to use the data type converter in JAXB, since JAXB must be
     * able to parse ISO8601 date string according to the XML Schema specification.
     * 
     * @see javax.xml.bind.DatatypeConverter#parseDateTime(String)
     */
    DateFormat ISO8601_ms = Dates.format("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    DateFormat RFC1123 = Dates.format("EEE, dd MMM yyyy HH:mm:ss zzz");

    /**
     * <code>Sat, 13 Mar 2010 11:29:05 -0800</code>
     * <p>
     * Please keep in mind that the [day-of-week ","] is optional in RFC-2822.
     */
    DateFormat RFC2822 = Dates.format("EEE, dd MMM yyyy HH:mm:ss Z");

    DateFormat RFC822_w2 = Dates.format("EEE, d MMM yy HH:mm z");
    DateFormat RFC822_w2s = Dates.format("EEE, d MMM yy HH:mm:ss z");
    DateFormat RFC822_w4 = Dates.format("EEE, d MMM yyyy HH:mm z");
    DateFormat RFC822_w4s = Dates.format("EEE, d MMM yyyy HH:mm:ss z");
    DateFormat RFC822_2 = Dates.format("d MMM yy HH:mm z");
    DateFormat RFC822_2s = Dates.format("d MMM yy HH:mm:ss z");
    DateFormat RFC822_4 = Dates.format("d MMM yyyy HH:mm z");
    DateFormat RFC822_4s = Dates.format("d MMM yyyy HH:mm:ss z");
    DateFormat RFC822 = RFC822_w4s;
    DateFormat[] RFC822_ALL = { RFC822_w2, RFC822_w2s, RFC822_w4, RFC822_w4s, RFC822_2, RFC822_2s, RFC822_4, RFC822_4s, };

    /** <code>1980-12-31</code> */
    DateFormat YYYY_MM_DD = Dates.format("yyyy-MM-dd");
    /** <code>1980-12</code> */
    DateFormat YYYY_MM = Dates.format("yyyy-MM");
    /** <code>1980-1</code> */
    DateFormat YYYY_M = Dates.format("yyyy-M");
    /** <code>80-12-31</code> */
    DateFormat YY_MM_DD = Dates.format("yy-MM-dd");
    /** <code>12/31/1980</code> */
    DateFormat MM_DD_YYYY = Dates.format("MM/dd/yyyy");
    /** <code>12/31/80</code> */
    DateFormat MM_DD_YY = Dates.format("MM/dd/yy");
    /** <code>19801231</code> */
    DateFormat YYYYMMDD = Dates.format("yyyyMMdd");
    /** <code>801231</code> */
    DateFormat YYMMDD = Dates.format("yyMMdd");

    /** <code>17:30:59</code> */
    DateFormat HH_MM_SS = Dates.format("hh:mm:ss");
    /** <code>173059</code> */
    DateFormat HHMMSS = Dates.format("hhmmss");

    /** <code>1980-12-31 17:30:59</code> */
    DateFormat D10T8 = Dates.format("yyyy-MM-dd hh:mm:ss");
    /** <code>17:30:59 12/31/1980</code> */
    DateFormat T8D10 = Dates.format("hh:mm:ss MM/dd/yyyy");
    /** <code>19801231-173059</code> */
    DateFormat D8T6 = Dates.format("yyyyMMdd-hhmmss");
    /** <code>801231-173059</code> */
    DateFormat D6T6 = Dates.format("yyMMdd-hhmmss");

}
