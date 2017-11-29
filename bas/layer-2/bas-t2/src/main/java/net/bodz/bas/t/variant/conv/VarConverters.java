package net.bodz.bas.t.variant.conv;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import net.bodz.bas.c.primitive.Primitives;

public class VarConverters {

    static Map<Class<?>, IVarConverter<?>> map = new HashMap<>();

    static {
        map.put(byte.class, ByteVarConverter.instance);
        map.put(Byte.class, ByteVarConverter.instance);
        map.put(short.class, ShortVarConverter.instance);
        map.put(Short.class, ShortVarConverter.instance);
        map.put(int.class, IntegerVarConverter.instance);
        map.put(Integer.class, IntegerVarConverter.instance);
        map.put(long.class, LongVarConverter.instance);
        map.put(Long.class, LongVarConverter.instance);
        map.put(float.class, FloatVarConverter.instance);
        map.put(Float.class, FloatVarConverter.instance);
        map.put(double.class, DoubleVarConverter.instance);
        map.put(Double.class, DoubleVarConverter.instance);
        map.put(boolean.class, BooleanVarConverter.instance);
        map.put(Boolean.class, BooleanVarConverter.instance);
        map.put(char.class, CharacterVarConverter.instance);
        map.put(Character.class, CharacterVarConverter.instance);
        map.put(String.class, StringVarConverter.instance);

        map.put(BigInteger.class, BigIntegerVarConverter.instance);
        map.put(BigDecimal.class, BigDecimalVarConverter.instance);

        map.put(Calendar.class, CalendarVarConverter.instance);
        map.put(Date.class, DateVarConverter.instance);
        map.put(java.sql.Date.class, SqlDateVarConverter.instance);
        map.put(DateTime.class, DateTimeVarConverter.instance);
    }

    public static <T> IVarConverter<T> getConverter(Class<?> type) {
        if (type.isPrimitive())
            type = Primitives.box(type);
        IVarConverter<T> converter = (IVarConverter<T>) map.get(type);
        return converter;
    }

}
