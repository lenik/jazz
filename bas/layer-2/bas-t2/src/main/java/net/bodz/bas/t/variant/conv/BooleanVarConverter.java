package net.bodz.bas.t.variant.conv;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.Literals;

public class BooleanVarConverter
        extends AbstractVarConverter<Boolean> {

    public BooleanVarConverter() {
        super(Boolean.class);

    }

    @Override
    public Boolean fromNumber(Number in)
            throws TypeConvertException {
        return in.intValue() != 0;
    }

    @Override
    public Boolean fromString(String in) {
        return _fromString(in);
    }

    public static Boolean _fromString(String in)
            throws TypeConvertException {
        in = in.trim().toLowerCase();
        if (Literals.trueValues.contains(in))
            return true;
        if (Literals.falseValues.contains(in))
            return false;
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
