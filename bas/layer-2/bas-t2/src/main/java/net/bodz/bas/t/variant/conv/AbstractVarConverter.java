package net.bodz.bas.t.variant.conv;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.fn.AbstractTransformer;
import net.bodz.bas.fn.ITransformer;

public abstract class AbstractVarConverter<T>
        implements IVarConverter<T> {

    protected final Class<T> type;
    final Map<Class<?>, ITransformer<Object, T>> frommap = new HashMap<Class<?>, ITransformer<Object, T>>();
    final Map<Class<?>, ITransformer<T, ?>> tomap = new HashMap<Class<?>, ITransformer<T, ?>>();
    final Set<IVarConverterExtension<T>> extensions;

    public AbstractVarConverter(Class<T> type) {
        this.type = type;

        frommap.put(type, new CastTr());
        frommap.put(String.class, new FromStringTr());
        frommap.put(Number.class, new FromNumberTr());
        frommap.put(byte[].class, new FromByteArrayTr());
        frommap.put(String[].class, new FromStringArrayTr());

        tomap.put(Byte.class, new ToByteTr());
        tomap.put(Short.class, new ToShortTr());
        tomap.put(Integer.class, new ToIntTr());
        tomap.put(Long.class, new ToLongTr());

        tomap.put(type, AbstractTransformer.Nop.<T> getInstance());
        tomap.put(Float.class, new ToFloatTr());
        tomap.put(Double.class, new ToDoubleTr());
        tomap.put(Boolean.class, new ToBooleanTr());
        tomap.put(Character.class, new ToCharTr());
        tomap.put(String.class, new ToStringTr());
        tomap.put(BigInteger.class, new ToBigIntegerTr());
        tomap.put(BigDecimal.class, new ToBigDecimalTr());
        tomap.put(Calendar.class, new ToCalendarTr());
        tomap.put(Date.class, new ToDateTr());
        tomap.put(java.sql.Date.class, new ToSqlDateTr());
        tomap.put(DateTime.class, new ToDateTimeTr());

        extensions = VarConverterExtensions.getExtensions(type);
        Iterator<IVarConverterExtension<T>> it = extensions.iterator();
        while (it.hasNext()) {
            IVarConverterExtension<T> ext = it.next();

            Map<Class<?>, ITransformer<Object, T>> fm = ext.getFromMap();
            for (Class<?> t : fm.keySet())
                if (!frommap.containsKey(t))
                    frommap.put(t, fm.get(t));

            Map<Class<?>, ITransformer<T, Object>> tm = ext.getToMap();
            for (Class<?> t : tm.keySet())
                if (!tomap.containsKey(t))
                    tomap.put(t, tm.get(t));
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public boolean canConvertFrom(Class<?> type) {
        Class<?> sup = type;
        while (sup != null) {
            if (frommap.containsKey(sup))
                return true;
            sup = sup.getSuperclass();
        }

        for (IVarConverterExtension<T> ext : extensions)
            if (ext.canConvertFrom(type))
                return true;

        return false;
    }

    @Override
    public boolean canConvertTo(Class<?> type) {
        if (tomap.containsKey(type))
            return true;

        for (IVarConverterExtension<T> ext : extensions)
            if (ext.canConvertTo(type))
                return true;

        return false;
    }

    @Override
    public final T from(Object obj)
            throws TypeConvertException {
        if (obj == null)
            return null;
        else
            return from(obj.getClass(), obj);
    }

    @Override
    public final T from(Class<?> type, Object obj)
            throws TypeConvertException {
        if (obj == null)
            throw new NullPointerException("in");
        if (this.type == type)
            return this.type.cast(obj);

        Class<?> sup = type;
        while (sup != null) {
            ITransformer<Object, T> fn = frommap.get(sup);
            if (fn != null)
                return fn.transform(obj);
            sup = sup.getSuperclass();
        }

        for (IVarConverterExtension<T> ext : extensions)
            if (ext.canConvertFrom(type))
                return ext.convertFrom(type, obj);

        return fromImpl(type, obj);
    }

    protected T fromImpl(Class<?> type, Object in) {
        throw new IllegalArgumentException(String.format(//
                "Can't convert to %s from %s. ", getType(), type));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> U to(T value, Class<U> type)
            throws TypeConvertException {
        if (type == null)
            throw new NullPointerException("type");
        if (this.type == type)
            return type.cast(value);

        Class<?> boxedType = Primitives.box(type);
        ITransformer<T, ?> fn = tomap.get(boxedType);
        if (fn != null)
            return (U) fn.transform(value);

        for (IVarConverterExtension<T> ext : extensions)
            if (ext.canConvertTo(type))
                return ext.convertTo(value, type);

        return toImpl(value, type);
    }

    protected <U> U toImpl(T value, Class<U> type)
            throws TypeConvertException {
        throw new IllegalArgumentException("Can't convert to this type: " + type);
    }

    @Override
    public T fromByteArray(byte[] in) {
        try {
            String s = new String(in, "utf-8");
            return fromString(s);
        } catch (UnsupportedEncodingException e) {
            throw new TypeConvertException("Can't decode utf-8 string.", e);
        }
    }

    @Override
    public T fromStringArray(String[] in) {
        if (in == null)
            throw new NullPointerException("in");
        if (in.length == 0)
            return null;
        String last = in[in.length - 1];
        return fromString(last);
    }

    @Override
    public byte toByte(T value) {
        return toNumber(value).byteValue();
    }

    @Override
    public short toShort(T value) {
        return toNumber(value).shortValue();
    }

    @Override
    public int toInt(T value) {
        return toNumber(value).intValue();
    }

    @Override
    public long toLong(T value) {
        return toNumber(value).longValue();
    }

    @Override
    public float toFloat(T value) {
        return toNumber(value).floatValue();
    }

    @Override
    public double toDouble(T value) {
        return toNumber(value).doubleValue();
    }

    @Override
    public char toChar(T value) {
        String s = toString(value);
        if (s == null)
            return 0;
        else if (s.isEmpty())
            return 0;
        else
            return s.charAt(0);
    }

    @Override
    public String toString(T value) {
        return value.toString();
    }

    @Override
    public BigInteger toBigInteger(T value) {
        long lval = toLong(value);
        return BigInteger.valueOf(lval);
    }

    @Override
    public BigDecimal toBigDecimal(T value) {
        double fval = toDouble(value);
        return BigDecimal.valueOf(fval);
    }

    @Override
    public Calendar toCalendar(T value) {
        return CalendarVarConverter.INSTANCE.from(value);
    }

    @Override
    public Date toDate(T value) {
        return DateVarConverter.INSTANCE.from(value);
    }

    @Override
    public java.sql.Date toSqlDate(T value) {
        return SqlDateVarConverter.INSTANCE.from(value);
    }

    @Override
    public DateTime toDateTime(T value) {
        return DateTimeVarConverter.INSTANCE.from(value);
    }

    class CastTr
            extends AbstractTransformer<Object, T> {
        @Override
        public T transform(Object input) {
            return AbstractVarConverter.this.type.cast(input);
        }
    }

    class FromStringTr
            extends AbstractTransformer<Object, T> {
        @Override
        public T transform(Object input) {
            return fromString((String) input);
        };
    }

    class FromNumberTr
            extends AbstractTransformer<Object, T> {
        @Override
        public T transform(Object input) {
            return fromNumber((Number) input);
        };
    }

    class FromByteArrayTr
            extends AbstractTransformer<Object, T> {
        @Override
        public T transform(Object input) {
            return fromByteArray((byte[]) input);
        };
    }

    class FromStringArrayTr
            extends AbstractTransformer<Object, T> {
        @Override
        public T transform(Object input) {
            return fromStringArray((String[]) input);
        };
    }

    class ToByteTr
            extends AbstractTransformer<T, Byte> {
        @Override
        public Byte transform(T input) {
            return toByte(input);
        }
    }

    class ToShortTr
            extends AbstractTransformer<T, Short> {
        @Override
        public Short transform(T input) {
            return toShort(input);
        }
    }

    class ToIntTr
            extends AbstractTransformer<T, Integer> {
        @Override
        public Integer transform(T input) {
            return toInt(input);
        }
    }

    class ToLongTr
            extends AbstractTransformer<T, Long> {
        @Override
        public Long transform(T input) {
            return toLong(input);
        }
    }

    class ToFloatTr
            extends AbstractTransformer<T, Float> {
        @Override
        public Float transform(T input) {
            return toFloat(input);
        }
    }

    class ToDoubleTr
            extends AbstractTransformer<T, Double> {
        @Override
        public Double transform(T input) {
            return toDouble(input);
        }
    }

    class ToBooleanTr
            extends AbstractTransformer<T, Boolean> {
        @Override
        public Boolean transform(T input) {
            return toBoolean(input);
        }
    }

    class ToCharTr
            extends AbstractTransformer<T, Character> {
        @Override
        public Character transform(T input) {
            return toChar(input);
        }
    }

    class ToStringTr
            extends AbstractTransformer<T, String> {
        @Override
        public String transform(T input) {
            return AbstractVarConverter.this.toString(input);
        }
    }

    class ToBigIntegerTr
            extends AbstractTransformer<T, BigInteger> {
        @Override
        public BigInteger transform(T input) {
            return toBigInteger(input);
        }
    }

    class ToBigDecimalTr
            extends AbstractTransformer<T, BigDecimal> {
        @Override
        public BigDecimal transform(T input) {
            return toBigDecimal(input);
        }
    }

    class ToCalendarTr
            extends AbstractTransformer<T, Calendar> {
        @Override
        public Calendar transform(T input) {
            return toCalendar(input);
        }
    }

    class ToDateTr
            extends AbstractTransformer<T, Date> {
        @Override
        public Date transform(T input) {
            return toDate(input);
        }
    }

    class ToDateTimeTr
            extends AbstractTransformer<T, DateTime> {
        @Override
        public DateTime transform(T input) {
            return toDateTime(input);
        }
    }

    class ToSqlDateTr
            extends AbstractTransformer<T, java.sql.Date> {
        @Override
        public java.sql.Date transform(T input) {
            return toSqlDate(input);
        }
    }

}
