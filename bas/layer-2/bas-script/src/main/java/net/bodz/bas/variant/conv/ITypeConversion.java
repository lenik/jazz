package net.bodz.bas.variant.conv;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import net.bodz.bas.err.TypeConvertException;

public interface ITypeConversion {

    BigDecimal fromObject(Object o)
            throws TypeConvertException;

    BigDecimal fromString(String str)
            throws TypeConvertException;

    byte toByte(BigDecimal value);

    short toShort(BigDecimal value);

    int toInt(BigDecimal value);

    long toLong(BigDecimal value);

    float toFloat(BigDecimal value);

    double toDouble(BigDecimal value);

    boolean toBoolean(BigDecimal value);

    char toChar(BigDecimal value);

    BigInteger toBigInteger(BigDecimal value);

    BigDecimal toBigDecimal(BigDecimal value);

    Date toDate(BigDecimal value);

}
