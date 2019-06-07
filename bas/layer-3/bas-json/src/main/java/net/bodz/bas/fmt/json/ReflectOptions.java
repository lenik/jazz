package net.bodz.bas.fmt.json;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReflectOptions {

    public static final Set<Class<?>> stopClasses = new HashSet<>();
    public static final Set<Class<?>> copyTypes = new HashSet<>();
    {
        stopClasses.add(Object.class);

        Class<?>[] types1 = { byte.class, short.class, int.class, long.class, float.class, double.class, boolean.class,
                Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class,
                BigDecimal.class, };
        copyTypes.addAll(Arrays.asList(types1));
    }

}
