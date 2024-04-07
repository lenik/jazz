package net.bodz.bas.t.variant.conv;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

import net.bodz.bas.err.TypeConvertException;

public class StringVarConverter
        extends AbstractVarConverter<String> {

    public StringVarConverter() {
        super(String.class);
    }

    @Override
    public boolean canConvertFrom(Class<?> type) {
        return super.canConvertFrom(type);
    }

    @Override
    public String fromNumber(Number in)
            throws TypeConvertException {
        return in.toString();
    }

    @Override
    public String fromString(String in)
            throws TypeConvertException {
        return in;
    }

    @Override
    public BigInteger toBigInteger(String value) {
        if ("undefined".equals(value))
            return null;
        return new BigInteger(value);
    }

    @Override
    public BigDecimal toBigDecimal(String value) {
        if ("undefined".equals(value))
            return null;
        return new BigDecimal(value);
    }

    @Override
    public Number toNumber(String str) {
        if (str == null)
            return null;

        if ("NaN".equals(str))
            return Double.NaN;
        if ("Infinity".equals(str))
            return Double.POSITIVE_INFINITY;
        if ("-Infinity".equals(str))
            return Double.NEGATIVE_INFINITY;

        boolean negative = str.startsWith("-");
        if (negative)
            str = str.substring(1);
        while (str.startsWith("0")) {
            if (str.length() == 1)
                break;
            if (str.charAt(1) == '.')
                break;
            str = str.substring(1);
        }

        StringBuilder sb = new StringBuilder(str.length());
        int len = str.length();
        boolean exp = false;
        boolean decimal = false;
        boolean start0 = false;
        int radix = 10;
        int pos = 0;
        for (int i = 0; i < len; i++, pos++) {
            char ch = str.charAt(i);
            switch (ch) {
            // case ' ':
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
            throw new TypeConvertException("Failed to parse number: " + str, e);
        }
    }

    @Override
    public boolean toBoolean(String in) {
        return BooleanVarConverter._fromString(in);
    }

    @Override
    public Instant toInstant(String in) {
        if (in == null)
            return null;
        return Instant.parse(in);
    }

    @Override
    public LocalDateTime toLocalDateTime(String in) {
        if (in == null)
            return null;
        return LocalDateTime.parse(in);
    }

    @Override
    public LocalDate toLocalDate(String in) {
        if (in == null)
            return null;
        return LocalDate.parse(in);
    }

    @Override
    public LocalTime toLocalTime(String in) {
        if (in == null)
            return null;
        return LocalTime.parse(in);
    }

    @Override
    public OffsetDateTime toOffsetDateTime(String in) {
        if (in == null)
            return null;
        return OffsetDateTime.parse(in);
    }

    @Override
    public OffsetTime toOffsetTime(String in) {
        if (in == null)
            return null;
        return OffsetTime.parse(in);
    }

    @Override
    public ZonedDateTime toZonedDateTime(String in) {
        if (in == null)
            return null;
        return ZonedDateTime.parse(in);
    }

    public static final StringVarConverter INSTANCE = new StringVarConverter();

}
