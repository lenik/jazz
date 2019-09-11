package net.bodz.bas.c.object;

import java.util.HashMap;
import java.util.Map;

public class TrueValues {

    static Map<Class<?>, Object> trueValueMap = new HashMap<Class<?>, Object>();
    static {
        trueValueMap.put(boolean.class, true);
        trueValueMap.put(Boolean.class, true);

        trueValueMap.put(byte.class, (byte) 1);
        trueValueMap.put(Byte.class, (byte) 1);

        trueValueMap.put(short.class, (short) 1);
        trueValueMap.put(Short.class, (short) 1);

        trueValueMap.put(int.class, 1);
        trueValueMap.put(Integer.class, 1);

        trueValueMap.put(long.class, 1L);
        trueValueMap.put(long.class, 1L);

        trueValueMap.put(float.class, 1.0f);
        trueValueMap.put(Float.class, 1.0f);

        trueValueMap.put(double.class, 1.0);
        trueValueMap.put(Double.class, 1.0);

        trueValueMap.put(char.class, '1');
        trueValueMap.put(Character.class, '1');

        trueValueMap.put(String.class, "true");
    }

    public static <T> T getTrueValue(Class<?> type) {
        T value = (T) trueValueMap.get(type);
        return value;
    }

}
