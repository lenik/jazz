package net.bodz.bas.util.primitive;

import java.util.HashMap;
import java.util.Map;

public class Boxing {

    private static Map<Class<?>, Class<?>> p2w;
    private static Map<Class<?>, Class<?>> w2p;
    static {
        p2w = new HashMap<Class<?>, Class<?>>();
        p2w.put(void.class, Void.class);
        p2w.put(byte.class, Byte.class);
        p2w.put(short.class, Short.class);
        p2w.put(int.class, Integer.class);
        p2w.put(long.class, Long.class);
        p2w.put(float.class, Float.class);
        p2w.put(double.class, Double.class);
        p2w.put(char.class, Character.class);
        p2w.put(boolean.class, Boolean.class);
        w2p = new HashMap<Class<?>, Class<?>>();
        w2p.put(Void.class, void.class);
        w2p.put(Byte.class, byte.class);
        w2p.put(Short.class, short.class);
        w2p.put(Integer.class, int.class);
        w2p.put(Long.class, long.class);
        w2p.put(Float.class, float.class);
        w2p.put(Double.class, double.class);
        w2p.put(Character.class, char.class);
        w2p.put(Boolean.class, boolean.class);
    }

    public static Class<?> box(Class<?> type) {
        return type.isPrimitive() ? p2w.get(type) : type;
    }

    public static Class<?> unbox(Class<?> type) {
        Class<?> primitive = w2p.get(type);
        return primitive == null ? type : primitive;
    }

}
