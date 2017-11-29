package net.bodz.bas.t.variant.conv;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IVarConverter<T>
        extends IPriority {

    Class<T> getType();

    boolean canConvertFrom(Class<?> type);

    boolean canConvertTo(Class<?> type);

    T from(Object obj)
            throws TypeConvertException;

    T from(Class<?> type, Object obj)
            throws TypeConvertException;

    T fromString(String in)
            throws TypeConvertException;

    T fromByteArray(byte[] in);

    T fromStringArray(String[] in);

    <U> U to(T value, Class<U> type);

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

    Calendar toCalendar(T value);

    Date toDate(T value);

    java.sql.Date toSqlDate(T value);

    DateTime toDateTime(T value);

}
