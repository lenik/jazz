package net.bodz.bas.t.variant.conv;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.bodz.bas.err.TypeConvertException;

public class BigIntegerVarConverter
        extends AbstractVarConverter<BigInteger> {

    public BigIntegerVarConverter() {
        super(BigInteger.class);
    }

    @Override
    public BigInteger fromNumber(Number in)
            throws TypeConvertException {
        Class<?> type = in.getClass();

        if (type == BigDecimal.class)
            return ((BigDecimal) in).toBigInteger();

        if (type == Float.class || type == Double.class) {
            double val = in.doubleValue();
            new BigDecimal(val).toBigInteger();
        }

        long longValue = in.longValue();
        return BigInteger.valueOf(longValue);
    }

    @Override
    public BigInteger fromString(String in)
            throws TypeConvertException {
        return new BigInteger(in);
    }

    @Override
    public BigInteger toNumber(BigInteger value) {
        return value;
    }

    @Override
    public byte toByte(BigInteger value) {
        return value.byteValue();
    }

    @Override
    public short toShort(BigInteger value) {
        return value.shortValue();
    }

    @Override
    public int toInt(BigInteger value) {
        return value.intValue();
    }

    @Override
    public long toLong(BigInteger value) {
        return value.longValue();
    }

    @Override
    public float toFloat(BigInteger value) {
        return value.floatValue();
    }

    @Override
    public double toDouble(BigInteger value) {
        return value.doubleValue();
    }

    @Override
    public boolean toBoolean(BigInteger value) {
        return value.intValue() != 0;
    }

    @Override
    public BigInteger toBigInteger(BigInteger value) {
        return value;
    }

    @Override
    public BigDecimal toBigDecimal(BigInteger value) {
        return new BigDecimal(value);
    }

    public static final BigIntegerVarConverter INSTANCE = new BigIntegerVarConverter();

}
