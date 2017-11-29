package net.bodz.bas.t.variant.conv;

import net.bodz.bas.err.TypeConvertException;

public class BooleanVarConverter
        extends AbstractVarConverter<Boolean> {

    public BooleanVarConverter() {
        super(Boolean.class);
    }

    @Override
    public Boolean fromString(String in) {
        return _fromString(in);
    }

    public static Boolean _fromString(String in)
            throws TypeConvertException {
        in = in.trim().toLowerCase();
        switch (in) {
        case "true":
        case "on":
        case "1":
            return true;

        case "false":
        case "off":
        case "0":
        case "":
            return false;
        }
        throw new TypeConvertException("Invalid boolean: " + in);
    }

    @Override
    public Number toNumber(Boolean value) {
        if (value == Boolean.TRUE)
            return 1;
        else
            return 0;
    }

    @Override
    public boolean toBoolean(Boolean value) {
        return value;
    }

    public static final BooleanVarConverter INSTANCE = new BooleanVarConverter();

}
