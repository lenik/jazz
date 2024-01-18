package net.bodz.bas.t.variant.conv;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IVarConverter<T>
        extends
            IPriority {

    Class<T> getType();

    boolean canConvertFrom(Class<?> type);

    boolean canConvertTo(Class<?> type);

    T from(Object obj)
            throws TypeConvertException;

    T from(Class<?> type, Object obj)
            throws TypeConvertException;

    T fromNumber(Number in)
            throws TypeConvertException;

    T fromString(String in)
            throws TypeConvertException;

    // T fromInstant()

    T fromByteArray(byte[] in);

    T fromStringArray(String[] in);

    <U> U to(T value, Class<U> type)
            throws TypeConvertException;

    Number toNumber(T value);

    byte toByte(T value);

    short toShort(T value);

    int toInt(T value);

    long toLong(T value);

    float toFloat(T value);

    double toDouble(T value);

    boolean toBoolean(T value);

    String toString(T value);

    char toChar(T value);

    BigInteger toBigInteger(T value);

    BigDecimal toBigDecimal(T value);

    Instant toInstant(T value);

    LocalDateTime toLocalDateTime(T value);

    LocalDate toLocalDate(T value);

    LocalTime toLocalTime(T value);

}
