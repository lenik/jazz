package net.bodz.bas.c.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;

public class TypeKind {

    /**
     * Normal class type is a type which is not primitive, interface, or array.
     *
     * @return <code>true</code> For normal class.
     */
    public static boolean isNormalClass(Class<?> type) {
        if (type.isArray() || type.isInterface() || type.isPrimitive())
            return false;
        return true;
    }

    /**
     * Whether the type is boolean or its boxed type.
     */
    public static boolean isBoolean(Class<?> type) {
        return type == boolean.class || type == Boolean.class;
    }

    static Set<Class<?>> setOf(Class<?>... v) {
        HashSet<Class<?>> set = new HashSet<>();
        for (Class<?> c : v)
            set.add(c);
        return set;
    }

    static Set<Class<?>> integerTypes = setOf(//
            byte.class, Byte.class, //
            short.class, Short.class, //
            int.class, Integer.class, //
            long.class, Long.class, //
            BigInteger.class);

    static Set<Class<?>> numericTypes = setOf(//
            byte.class, Byte.class, //
            short.class, Short.class, //
            int.class, Integer.class, //
            long.class, Long.class, //
            float.class, Float.class, //
            double.class, Double.class, //
            BigInteger.class, //
            BigDecimal.class);

    /**
     * Whether the type is byte, short, int, long, or their boxed type.
     */
    public static boolean isInteger(Class<?> type) {
        return integerTypes.contains(type);
    }

    public static boolean isNumeric(Class<?> type) {
        return numericTypes.contains(type);
    }

    public static AggregationEnum getAggregationEnum(Class<?> type) {
        if (type.isArray())
            return AggregationEnum.ARRAY;

        if (List.class.isAssignableFrom(type))
            return AggregationEnum.LIST;

        if (Map.class.isAssignableFrom(type))
            return AggregationEnum.MAP;

        if (Set.class.isAssignableFrom(type))
            return AggregationEnum.SET;

        return AggregationEnum.SCALAR;
    }

    static final Map<Class<?>, Integer> typeIdMap;
    static {
        typeIdMap = new HashMap<>();
        typeIdMap.put(void.class, TypeId._void);
        typeIdMap.put(char.class, TypeId._char);
        typeIdMap.put(byte.class, TypeId._byte);
        typeIdMap.put(short.class, TypeId._short);
        typeIdMap.put(int.class, TypeId._int);
        typeIdMap.put(long.class, TypeId._long);
        typeIdMap.put(float.class, TypeId._float);
        typeIdMap.put(double.class, TypeId._double);
        typeIdMap.put(boolean.class, TypeId._boolean);
        typeIdMap.put(Void.class, TypeId.VOID);
        typeIdMap.put(Character.class, TypeId.CHARACTER);
        typeIdMap.put(Byte.class, TypeId.BYTE);
        typeIdMap.put(Short.class, TypeId.SHORT);
        typeIdMap.put(Integer.class, TypeId.INTEGER);
        typeIdMap.put(Long.class, TypeId.LONG);
        typeIdMap.put(Float.class, TypeId.FLOAT);
        typeIdMap.put(Double.class, TypeId.DOUBLE);
        typeIdMap.put(Boolean.class, TypeId.BOOLEAN);

        typeIdMap.put(Class.class, TypeId.CLASS);
        typeIdMap.put(String.class, TypeId.STRING);
        typeIdMap.put(StringBuffer.class, TypeId.STRING_BUFFER);
        typeIdMap.put(System.class, TypeId.SYSTEM);
        typeIdMap.put(Error.class, TypeId.ERROR);
        typeIdMap.put(Throwable.class, TypeId.THROWABLE);

        typeIdMap.put(BigInteger.class, TypeId.BIG_INTEGER);
        typeIdMap.put(BigDecimal.class, TypeId.BIG_DECIMAL);

        typeIdMap.put(RuntimeException.class, TypeId.RUNTIME_EXCEPTION);
        typeIdMap.put(Exception.class, TypeId.EXCEPTION);
        typeIdMap.put(Iterable.class, TypeId.ITERABLE);
        typeIdMap.put(StringBuilder.class, TypeId.STRING_BUILDER);
        typeIdMap.put(Enum.class, TypeId.ENUM);
        typeIdMap.put(Deprecated.class, TypeId.DEPRECATED);
        typeIdMap.put(Override.class, TypeId.OVERRIDE);
        typeIdMap.put(SuppressWarnings.class, TypeId.SUPPRESS_WARNINGS);
        typeIdMap.put(Date.class, TypeId.DATE);
        typeIdMap.put(java.sql.Date.class, TypeId.SQL_DATE);
        typeIdMap.put(DateTime.class, TypeId.JODA_DATETIME);
        typeIdMap.put(MutableDateTime.class, TypeId.JODA_DATETIME);
        typeIdMap.put(Calendar.class, TypeId.CALENDAR);
    }

    public static int getTypeId(Class<?> clazz) {
        Integer id = typeIdMap.get(clazz);
        if (id != null)
            return id.intValue();
        if (clazz.isEnum())
            return TypeId.ENUM;
        return TypeId.UNDEFINED;
    }

}
