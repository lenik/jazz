package net.bodz.bas.t.variant.conv;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections15.Transformer;
import org.joda.time.DateTime;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.err.TypeConvertException;

public abstract class AbstractVarConverter<T>
        implements IVarConverter<T> {

    final Map<Class<?>, Transformer<Object, T>> frommap = new HashMap<>();
    final Map<Class<?>, Transformer<T, ?>> tomap = new HashMap<>();

    public AbstractVarConverter() {
        frommap.put(String.class, new Transformer<Object, T>() {
            public T transform(Object input) {
                return fromString((String) input);
            };
        });
        frommap.put(byte[].class, new Transformer<Object, T>() {
            public T transform(Object input) {
                return fromByteArray((byte[]) input);
            };
        });
        frommap.put(String[].class, new Transformer<Object, T>() {
            public T transform(Object input) {
                return fromStringArray((String[]) input);
            };
        });

        tomap.put(Byte.class, new Transformer<T, Byte>() {
            @Override
            public Byte transform(T input) {
                return toByte(input);
            }
        });
        tomap.put(Short.class, new Transformer<T, Short>() {
            @Override
            public Short transform(T input) {
                return toShort(input);
            }
        });
        tomap.put(Integer.class, new Transformer<T, Integer>() {
            @Override
            public Integer transform(T input) {
                return toInt(input);
            }
        });
        tomap.put(Long.class, new Transformer<T, Long>() {
            @Override
            public Long transform(T input) {
                return toLong(input);
            }
        });

        tomap.put(Float.class, new Transformer<T, Float>() {
            @Override
            public Float transform(T input) {
                return toFloat(input);
            }
        });
        tomap.put(Double.class, new Transformer<T, Double>() {
            @Override
            public Double transform(T input) {
                return toDouble(input);
            }
        });
        tomap.put(Boolean.class, new Transformer<T, Boolean>() {
            @Override
            public Boolean transform(T input) {
                return toBoolean(input);
            }
        });
        tomap.put(Character.class, new Transformer<T, Character>() {
            @Override
            public Character transform(T input) {
                return toChar(input);
            }
        });
        tomap.put(String.class, new Transformer<T, String>() {
            @Override
            public String transform(T input) {
                return AbstractVarConverter.this.toString(input);
            }
        });
        tomap.put(BigInteger.class, new Transformer<T, BigInteger>() {
            @Override
            public BigInteger transform(T input) {
                return toBigInteger(input);
            }
        });
        tomap.put(BigDecimal.class, new Transformer<T, BigDecimal>() {
            @Override
            public BigDecimal transform(T input) {
                return toBigDecimal(input);
            }
        });
        tomap.put(Calendar.class, new Transformer<T, Calendar>() {
            @Override
            public Calendar transform(T input) {
                return toCalendar(input);
            }
        });
        tomap.put(Date.class, new Transformer<T, Date>() {
            @Override
            public Date transform(T input) {
                return toDate(input);
            }
        });
        tomap.put(java.sql.Date.class, new Transformer<T, java.sql.Date>() {
            @Override
            public java.sql.Date transform(T input) {
                return toSqlDate(input);
            }
        });
        tomap.put(DateTime.class, new Transformer<T, DateTime>() {
            @Override
            public DateTime transform(T input) {
                return toDateTime(input);
            }
        });
    }

    @Override
    public boolean canConvertFrom(Class<?> type) {
        return frommap.containsKey(type);
    }

    @Override
    public boolean canConvertTo(Class<?> type) {
        return tomap.containsKey(type);
    }

    @Override
    public final T from(Object obj) {
        return from(obj.getClass(), obj);
    }

    @Override
    public final T from(Class<?> type, Object obj)
            throws TypeConvertException {
        if (obj == null)
            throw new NullPointerException("in");
        Transformer<Object, T> fn = frommap.get(type);
        if (fn != null)
            return fn.transform(obj);
        else
            return fromImpl(type, obj);
    }

    protected T fromImpl(Class<?> type, Object in) {
        throw new TypeConvertException("Can't convert from this type: " + in.getClass());
    }

    @Override
    public <U> U to(T value, Class<U> type) {
        if (type == null)
            throw new NullPointerException("type");
        Class<?> boxed = Primitives.box(type);
        Transformer<T, ?> fn = tomap.get(boxed);
        if (fn != null)
            return (U) fn.transform(value);
        else
            return toImpl(value, type);
    }

    protected <U> U toImpl(T value, Class<U> type) {
        throw new TypeConvertException("Can't convert to this type: " + type);
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

    public Calendar toCalendar(T value) {
        return CalendarVarConverter.instance.from(value);
    }

    @Override
    public Date toDate(T value) {
        return DateVarConverter.instance.from(value);
    }

    @Override
    public java.sql.Date toSqlDate(T value) {
        return SqlDateVarConverter.instance.from(value);
    }

    @Override
    public DateTime toDateTime(T value) {
        return DateTimeVarConverter.instance.from(value);
    }

}
