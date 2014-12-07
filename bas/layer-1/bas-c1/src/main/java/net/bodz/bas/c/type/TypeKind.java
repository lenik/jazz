package net.bodz.bas.c.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

}
