package net.bodz.bas.t.variant.conv;

import net.bodz.bas.err.TypeConvertException;

public class DoubleVarConverter
        extends AbstractVarConverter<Double> {

    public DoubleVarConverter() {
        super(Double.class);
    }

    @Override
    public Double fromNumber(Number in)
            throws TypeConvertException {
        return in.doubleValue();
    }

    @Override
    public Double fromString(String in)
            throws TypeConvertException {
        try {
            return Double.valueOf(in);
        } catch (NumberFormatException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public Double toNumber(Double value) {
        return value;
    }

    @Override
    public byte toByte(Double value) {
        return value.byteValue();
    }

    @Override
    public short toShort(Double value) {
        return value.shortValue();
    }

    @Override
    public int toInt(Double value) {
        return value.intValue();
    }

    @Override
    public long toLong(Double value) {
        return value.longValue();
    }

    @Override
    public float toFloat(Double value) {
        return value.floatValue();
    }

    @Override
    public double toDouble(Double value) {
        return value.doubleValue();
    }

    @Override
    public boolean toBoolean(Double value) {
        return value.intValue() != 0;
    }

    public static final DoubleVarConverter INSTANCE = new DoubleVarConverter();

}
