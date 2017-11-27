package net.bodz.bas.t.variant.conv;

import net.bodz.bas.err.TypeConvertException;

public class ShortVarConverter
        extends AbstractVarConverter<Short> {

    @Override
    public Short fromString(String in)
            throws TypeConvertException {
        // TODO auto radix.
        return Short.valueOf(in);
    }

    @Override
    public Short toNumber(Short value) {
        return value;
    }

    @Override
    public byte toByte(Short value) {
        return value.byteValue();
    }

    @Override
    public short toShort(Short value) {
        return value.shortValue();
    }

    @Override
    public int toInt(Short value) {
        return value.shortValue() & 0xffff;
    }

    @Override
    public long toLong(Short value) {
        return (long) (value.shortValue() & 0xffff);
    }

    @Override
    public float toFloat(Short value) {
        return value.floatValue();
    }

    @Override
    public double toDouble(Short value) {
        return value.doubleValue();
    }

    @Override
    public boolean toBoolean(Short value) {
        return value.intValue() != 0;
    }

    public static final ShortVarConverter instance = new ShortVarConverter();

}
