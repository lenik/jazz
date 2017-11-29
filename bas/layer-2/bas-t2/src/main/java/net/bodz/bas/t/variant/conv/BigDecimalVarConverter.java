package net.bodz.bas.t.variant.conv;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.bodz.bas.err.TypeConvertException;

public class BigDecimalVarConverter
        extends AbstractVarConverter<BigDecimal> {

    public BigDecimalVarConverter() {
        super(BigDecimal.class);
    }

    @Override
    public BigDecimal fromString(String in)
            throws TypeConvertException {
        return new BigDecimal(in);
    }

    @Override
    public BigDecimal toNumber(BigDecimal value) {
        return value;
    }

    @Override
    public byte toByte(BigDecimal value) {
        return value.byteValue();
    }

    @Override
    public short toShort(BigDecimal value) {
        return value.shortValue();
    }

    @Override
    public int toInt(BigDecimal value) {
        return value.intValue();
    }

    @Override
    public long toLong(BigDecimal value) {
        return value.longValue();
    }

    @Override
    public float toFloat(BigDecimal value) {
        return value.floatValue();
    }

    @Override
    public double toDouble(BigDecimal value) {
        return value.doubleValue();
    }

    @Override
    public boolean toBoolean(BigDecimal value) {
        return value.intValue() != 0;
    }

    @Override
    public BigInteger toBigInteger(BigDecimal value) {
        return value.toBigInteger();
    }

    @Override
    public BigDecimal toBigDecimal(BigDecimal value) {
        return value;
    }

    public static final BigDecimalVarConverter INSTANCE = new BigDecimalVarConverter();

}
