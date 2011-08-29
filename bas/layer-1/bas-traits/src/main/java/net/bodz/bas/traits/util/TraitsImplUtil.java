package net.bodz.bas.traits.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.lang.mi.IQueryable;

public class TraitsImplUtil {

    static final Map<Class<?>, Object> traitsImplCache;
    static {
        traitsImplCache = new HashMap<Class<?>, Object>();
    }

    public static <T> T getTraits(Class<?> traitsImplType, Class<T> traitsType)
            throws ReflectiveOperationException {
        Object traitsImplInstance = traitsImplCache.get(traitsImplType);
        if (traitsImplInstance == null) {
            traitsImplInstance = Jdk7Reflect.newInstance(traitsImplType);
            traitsImplCache.put(traitsImplType, traitsImplInstance);
        }

        if (IQueryable.class.isAssignableFrom(traitsImplType)) {
            IQueryable querable = (IQueryable) traitsImplInstance;
            T traits = querable.query(traitsType);
            return traits;

        } else {
            return traitsType.cast(traitsImplInstance);
        }

    }

}
