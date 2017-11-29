package net.bodz.bas.t.variant.conv;

import net.bodz.bas.err.TypeConvertException;

public class LongVarConverter
        extends AbstractVarConverter<Long> {

    public LongVarConverter() {
        super(Long.class);
    }

    @Override
    public Long fromString(String in)
            throws TypeConvertException {
        // TODO auto radix.
        return Long.valueOf(in);
    }

    @Override
    public Long toNumber(Long value) {
        return value;
    }

    @Override
    public byte toByte(Long value) {
        return value.byteValue();
    }

    @Override
    public short toShort(Long value) {
        return value.shortValue();
    }

    @Override
    public int toInt(Long value) {
        return value.intValue();
    }

    @Override
    public long toLong(Long value) {
        return value.longValue();
    }

    @Override
    public float toFloat(Long value) {
        return value.floatValue();
    }

    @Override
    public double toDouble(Long value) {
        return value.doubleValue();
    }

    @Override
    public boolean toBoolean(Long value) {
        return value.intValue() != 0;
    }

    public static final LongVarConverter instance = new LongVarConverter();

}
