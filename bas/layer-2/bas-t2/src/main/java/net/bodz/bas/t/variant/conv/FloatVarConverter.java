 package net.bodz.bas.t.variant.conv;

import net.bodz.bas.err.TypeConvertException;

public class FloatVarConverter
        extends AbstractVarConverter<Float> {

    @Override
    public Float fromString(String in)
            throws TypeConvertException {
        return Float.valueOf(in);
    }

    @Override
    public Float toNumber(Float value) {
        return value;
    }

    @Override
    public byte toByte(Float value) {
        return value.byteValue();
    }

    @Override
    public short toShort(Float value) {
        return value.shortValue();
    }

    @Override
    public int toInt(Float value) {
        return value.intValue();
    }

    @Override
    public long toLong(Float value) {
        return value.longValue();
    }

    @Override
    public float toFloat(Float value) {
        return value.floatValue();
    }

    @Override
    public double toDouble(Float value) {
        return value.doubleValue();
    }

    @Override
    public boolean toBoolean(Float value) {
        return value.intValue() != 0;
    }

    public static final FloatVarConverter instance = new FloatVarConverter();

}
