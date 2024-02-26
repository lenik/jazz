package net.bodz.bas.c.primitive;

import java.util.HashMap;
import java.util.Map;

public class Primitives {

    private static final Map<Class<?>, Class<?>> p2w = new HashMap<>();
    private static final Map<Class<?>, Class<?>> w2p = new HashMap<>();
    private static final Map<String, Class<?>> names = new HashMap<>();

    static void add(Class<?> type, Class<?> boxed) {
        p2w.put(type, boxed);
        w2p.put(boxed, type);
        String name = type.getName();
        names.put(name, type);
    }

    static {
        add(void.class, Void.class);
        add(byte.class, Byte.class);
        add(short.class, Short.class);
        add(int.class, Integer.class);
        add(long.class, Long.class);
        add(float.class, Float.class);
        add(double.class, Double.class);
        add(char.class, Character.class);
        add(boolean.class, Boolean.class);
    }

    public static Class<?> box(Class<?> type) {
        return type.isPrimitive() ? p2w.get(type) : type;
    }

    public static Class<?> unbox(Class<?> type) {
        Class<?> primitive = w2p.get(type);
        return primitive == null ? type : primitive;
    }

}
