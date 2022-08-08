package net.bodz.bas.t.variant.conv;

import java.math.BigDecimal;
import java.math.BigInteger;

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
        return new BigInteger(value);
    }

    @Override
    public BigDecimal toBigDecimal(String value) {
        return new BigDecimal(value);
    }

    @Override
    public Number toNumber(String value) {
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
            throw new TypeConvertException("Failed to parse number: " + value, e);
        }
    }

    @Override
    public boolean toBoolean(String in) {
        return BooleanVarConverter._fromString(in);
    }

    public static final StringVarConverter INSTANCE = new StringVarConverter();

}
