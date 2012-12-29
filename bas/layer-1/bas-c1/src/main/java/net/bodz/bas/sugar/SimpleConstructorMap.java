package net.bodz.bas.sugar;

import java.lang.reflect.Constructor;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.c.type.TypeDistance;
import net.bodz.bas.err.LazyLoadException;

public class SimpleConstructorMap
        extends ClassLocal<Constructor<?>> {

    private static final long serialVersionUID = 1L;

    final Class<?> type;

    /**
     * @see SimpleConstructorMap#getInstance(Class)
     */
    SimpleConstructorMap(Class<?> type) {
        super(new EntryLoader(type));
        this.type = type;
    }

    static class EntryLoader
            implements IMapEntryLoader<Class<?>, Constructor<?>> {

        final Class<?> type;

        public EntryLoader(Class<?> type) {
            if (type == null)
                throw new NullPointerException("type");
            this.type = type;
        }

        @Override
        public Constructor<?> loadValue(Class<?> actualType)
                throws LazyLoadException {

            Constructor<?> minctor = null;
            int mindist = Integer.MAX_VALUE;

            for (Constructor<?> ctor : type.getConstructors()) {
                Class<?>[] parameterTypes = ctor.getParameterTypes();
                if (parameterTypes.length != 1)
                    continue;

                Class<?> declType = parameterTypes[0];
                if (declType == actualType)
                    return ctor;

                int dist = TypeDistance.dist(declType, actualType);
                if (dist == -1)
                    continue;

                if (dist < mindist) {
                    mindist = dist;
                    minctor = ctor;
                }
            }
            return minctor;
        }

    } // EntryLoader

    static final String CLASS_LOCAL_ID = SimpleConstructorMap.class.getCanonicalName();
    static final ClassLocal<SimpleConstructorMap> toolsCtorMap;
    static {
        toolsCtorMap = ClassLocals.createMap(CLASS_LOCAL_ID, //
                new IMapEntryLoader<Class<?>, SimpleConstructorMap>() {
                    public SimpleConstructorMap loadValue(Class<?> type) {
                        return new SimpleConstructorMap(type);
                    }
                });
    }

    public static SimpleConstructorMap getInstance(Class<?> type) {
        return toolsCtorMap.getOrLoad(type);
    }

}
