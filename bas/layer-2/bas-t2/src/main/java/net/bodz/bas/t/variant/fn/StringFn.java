package net.bodz.bas.t.variant.fn;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.ParseException;

public class StringFn {

    public static byte parseByte(String s)
            throws ParseException {
        try {
            return Byte.parseByte(s);
        } catch (NumberFormatException e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static byte parseByte(String s, byte fallback) {
        if (s == null)
            return fallback;
        try {
            return Byte.parseByte(s);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public static short parseShort(String s)
            throws ParseException {
        try {
            return Short.parseShort(s);
        } catch (NumberFormatException e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static short parseShort(String s, short fallback) {
        if (s == null)
            return fallback;
        try {
            return Short.parseShort(s);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public static int parseInt(String s)
            throws ParseException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static int parseInt(String s, int fallback) {
        if (s == null)
            return fallback;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public static long parseLong(String s)
            throws ParseException {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static long parseLong(String s, long fallback) {
        if (s == null)
            return fallback;
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public static float parseFloat(String s)
            throws ParseException {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static float parseFloat(String s, float fallback) {
        if (s == null)
            return fallback;
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public static double parseDouble(String s)
            throws ParseException {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static double parseDouble(String s, double fallback) {
        if (s == null)
            return fallback;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public static Number parseNumber(String value)
            throws ParseException {
        return parseNumber(value, null);
    }

    public static Number parseNumber(String value, Number fallback)
            throws ParseException {
        if (value == null)
            return null;
        value = value.trim();
        if ("NaN".equals(value))
            return Double.NaN;
        if ("Infinity".equals(value))
            return Double.POSITIVE_INFINITY;
        if ("-Infinity".equals(value))
            return Double.NEGATIVE_INFINITY;

        boolean negative = value.startsWith("-");

        StringBuilder sb = new StringBuilder(value.length());
        int len = value.length();
        boolean exp = false;
        boolean decimal = false;
        boolean start0 = false;
        int radix = 10;
        int pos = 0;
        for (int i = negative ? 1 : 0; i < len; i++, pos++) {
            char ch = value.charAt(i);
            switch (ch) {
            case ' ':
            case '_':
                continue;
            case ',':
                // for some locale, ',' is the decimal separator.
                continue;
            case '.':
                decimal = true;
            case '0':
                if (pos == 0) {
                    start0 = true;
                    radix = 8;
                }
                break;
            case 'b':
                if (start0 && pos == 1)
                    radix = 2;
                break;
            case 'x':
                if (start0 && pos == 1)
                    radix = 16;
                break;
            case 'e':
            case 'E':
                exp = true;
            }
            sb.append(ch);
        }
        String compact = sb.toString();
        try {
            if (decimal || exp) {
                double fval = Double.parseDouble(compact);
                if (negative)
                    fval = -fval;
                return fval;
            } else {
                long lval = Long.parseLong(compact, radix);
                if (negative)
                    lval = -lval;
                return lval;
            }
        } catch (NumberFormatException e) {
            if (fallback != null)
                return fallback;
            throw new ParseException("Failed parse parse number: " + value, e);
        }
    }

    public static char parseCharacter(String s)
            throws ParseException {
        if (s == null)
            throw new NullPointerException("s");
        s = s.trim();
        if (s.isEmpty())
            throw new ParseException("string empty");
        return s.charAt(0);
    }

    public static char parseCharacter(String s, char fallback) {
        if (s == null)
            return fallback;
        s = s.trim();
        if (s.isEmpty())
            return fallback;
        return s.charAt(0);
    }

    public static BigInteger parseBigInteger(String s)
            throws ParseException {
        if (s == null)
            return null;
        try {
            return new BigInteger(s);
        } catch (Exception e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static BigInteger parseBigInteger(String s, BigInteger fallback) {
        if (s == null)
            return fallback;
        try {
            return new BigInteger(s);
        } catch (Exception e) {
            return fallback;
        }
    }

    public static BigDecimal parseBigDecimal(String s)
            throws ParseException {
        if (s == null)
            return null;
        try {
            return new BigDecimal(s);
        } catch (Exception e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static BigDecimal parseBigDecimal(String s, BigDecimal fallback) {
        if (s == null)
            return fallback;
        try {
            return new BigDecimal(s);
        } catch (Exception e) {
            return fallback;
        }
    }

    public static File parseFile(String s)
            throws ParseException {
        if (s == null)
            return null;
        return new File(s);
    }

    public static File parseFile(String s, File fallback) {
        if (s == null)
            return fallback;
        return new File(s);
    }

    public static URI parseURI(String s)
            throws ParseException {
        if (s == null)
            return null;
        try {
            return new URI(s);
        } catch (URISyntaxException e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static URI parseURI(String s, URI fallback) {
        if (s == null)
            return fallback;
        try {
            return new URI(s);
        } catch (URISyntaxException e) {
            return fallback;
        }
    }

    public static URL parseURL(String s)
            throws ParseException {
        if (s == null)
            return null;
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static URL parseURL(String s, URL fallback) {
        if (s == null)
            return fallback;
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            return fallback;
        }
    }

    public static Class<?> parseClass(String s)
            throws ParseException {
        if (s == null)
            return null;
        try {
            return Class.forName(s);
        } catch (Throwable e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static Class<?> parseClass(String s, Class<?> fallback) {
        if (s == null)
            return fallback;
        try {
            return parseClass(s);
        } catch (Throwable e) {
            return fallback;
        }
    }

    public static Date parseDate(String s)
            throws ParseException {
        if (s == null)
            return null;
        try {
            return Dates.ISO8601compat.parse(s);
        } catch (Exception e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static Date parseDate(String s, Date fallback) {
        if (s == null)
            return fallback;
        try {
            return Dates.ISO8601compat.parse(s);
        } catch (Exception e) {
            return fallback;
        }
    }

    public static DateTime parseDateTime(String s)
            throws ParseException {
        if (s == null)
            return null;
        try {
            return ISODateTimeFormat.dateTime().parseDateTime(s);
        } catch (Exception e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static DateTime parseDateTime(String s, DateTime fallback) {
        if (s == null)
            return fallback;
        try {
            return ISODateTimeFormat.dateTime().parseDateTime(s);
        } catch (Exception e) {
            return fallback;
        }
    }

    public static Path parsePath(String s)
            throws ParseException {
        if (s == null)
            return null;
        try {
            return Paths.get(s);
        } catch (InvalidPathException e) {
            throw new ParseException("error parse " + s, e);
        }
    }

    public static Path parsePath(String s, Path fallback) {
        if (s == null)
            return fallback;
        try {
            return Paths.get(s);
        } catch (InvalidPathException e) {
            return fallback;
        }
    }

    public static boolean parseBoolean(String in)
            throws ParseException {
        if (in == null)
            throw new NullPointerException("in");
        Boolean val = parseBoolean(in, null);
        if (val == null)
            throw new ParseException("bad boolean value: " + in);
        else
            return val.booleanValue();
    }

    public static boolean parseBoolean(String in, boolean fallback) {
        return parseBoolean(in, Boolean.valueOf(fallback));
    }

    public static Boolean parseBoolean(String in, Boolean fallback) {
        if (in == null)
            return fallback;
        in = in.trim().toLowerCase();
        switch (in) {
        case "true":
        case "yes":
        case "on":
            return Boolean.TRUE;

        case "false":
        case "no":
        case "off":
            return Boolean.FALSE;

        default:
            if (StringPred.isDecimal(in)) {
                int n = parseInt(in, -1);
                return n != 0;
            }
        }

        if (fallback != null)
            return fallback;
        return null;
    }

    public static String escapeJava(String s) {
        if (s == null)
            return null;
        return StringEscape.escapeJava(s);
    }

    public static String unescapeJava(String s) {
        if (s == null)
            return null;
        return StringEscape.unescapeJava(s);
    }

}
