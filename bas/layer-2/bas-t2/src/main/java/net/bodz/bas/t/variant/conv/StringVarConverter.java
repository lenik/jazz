package net.bodz.bas.t.variant.conv;

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
    public String fromString(String in)
            throws TypeConvertException {
        return in;
    }

    @Override
    public Number toNumber(String value) {
        StringBuilder sb = new StringBuilder(value.length());
        int len = value.length();
        boolean exp = false;
        boolean decimal = false;
        boolean start0 = false;
        int radix = 10;
        for (int i = 0; i < len; i++) {
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
                if (i == 0) {
                    start0 = true;
                    radix = 8;
                }
                break;
            case 'b':
                if (start0 && i == 1)
                    radix = 2;
                break;
            case 'x':
                if (start0 && i == 1)
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
                return Double.parseDouble(compact);
            } else {
                return Long.parseLong(compact, radix);
            }
        } catch (NumberFormatException e) {
            throw new TypeConvertException("Failed to parse number: " + value, e);
        }
    }

    @Override
    public boolean toBoolean(String in) {
        return BooleanVarConverter._fromString(in);
    }

    public static final StringVarConverter instance = new StringVarConverter();

}
