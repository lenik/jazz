package net.bodz.bas.sugar;

import java.lang.reflect.Constructor;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.c.type.TypeSpace;
import net.bodz.bas.err.LazyLoadException;

public class SimpleConstructorMap
        extends LazyTypeMap<Constructor<?>> {

    private static final long serialVersionUID = 1L;

    final Class<?> type;

    /**
     * @see SimpleConstructorMap#getInstance(Class)
     */
    SimpleConstructorMap(Class<?> type) {
        super(new EntryLoader(type));
        this.type = type;
    }

    static TypeSpace typeSpace = TypeSpace.getDefault();

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

            Constructor<?> minCtor = null;
            int minDist = Integer.MAX_VALUE;

            for (Constructor<?> ctor : type.getConstructors()) {
                Class<?>[] parameterTypes = ctor.getParameterTypes();
                if (parameterTypes.length != 1)
                    continue;

                Class<?> declType = parameterTypes[0];
                if (declType == actualType)
                    return ctor;

                int dist = typeSpace.dist(declType, actualType);
                if (dist == -1)
                    continue;

                if (dist < minDist) {
                    minDist = dist;
                    minCtor = ctor;
                }
            }
            return minCtor;
        }

    } // EntryLoader

    static final String CLS_TOOLS_CTOR_ID = SimpleConstructorMap.class.getCanonicalName();
    static final LazyTypeMap<SimpleConstructorMap> clsToolsCtorMap;
    static {
        clsToolsCtorMap = TypeMapRegistry.createMap(CLS_TOOLS_CTOR_ID, //
                new IMapEntryLoader<Class<?>, SimpleConstructorMap>() {
                    public SimpleConstructorMap loadValue(Class<?> type) {
                        return new SimpleConstructorMap(type);
                    }
                });
    }

    public static SimpleConstructorMap getInstance(Class<?> type) {
        return clsToolsCtorMap.getOrLoad(type);
    }

}
