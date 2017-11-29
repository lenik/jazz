package net.bodz.bas.t.variant.conv;

import net.bodz.bas.err.TypeConvertException;

public class IntegerVarConverter
        extends AbstractVarConverter<Integer> {

    public IntegerVarConverter() {
        super(Integer.class);
    }

    @Override
    public Integer fromString(String in)
            throws TypeConvertException {
        // TODO auto radix.
        return Integer.valueOf(in);
    }

    @Override
    public Integer toNumber(Integer value) {
        return value;
    }

    @Override
    public byte toByte(Integer value) {
        return value.byteValue();
    }

    @Override
    public short toShort(Integer value) {
        return value.shortValue();
    }

    @Override
    public int toInt(Integer value) {
        return value.intValue();
    }

    @Override
    public long toLong(Integer value) {
        return value.longValue();
    }

    @Override
    public float toFloat(Integer value) {
        return value.floatValue();
    }

    @Override
    public double toDouble(Integer value) {
        return value.doubleValue();
    }

    @Override
    public boolean toBoolean(Integer value) {
        return value.intValue() != 0;
    }

    public static final IntegerVarConverter INSTANCE = new IntegerVarConverter();

}
