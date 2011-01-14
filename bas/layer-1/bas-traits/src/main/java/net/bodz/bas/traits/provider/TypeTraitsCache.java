package net.bodz.bas.traits.provider;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.traits.ICommonTraits;

/**
 * XXX - apply ehcache etc. to the static map.
 */
public class TypeTraitsCache {

    private static final Map<Class<?>, ICommonTraits<?>> typeInfoMap;
    static {
        typeInfoMap = new HashMap<Class<?>, ICommonTraits<?>>();
    }

    @SuppressWarnings("unchecked")
    public static <T> ICommonTraits<T> getTypeInfo(Class<T> type) {
        return (ICommonTraits<T>) typeInfoMap.get(type);
    }

    public static <T> void setTypeInfo(Class<T> type, ICommonTraits<T> typeInfo) {
        typeInfoMap.put(type, typeInfo);
    }

    /**
     * Get type info from the cache, or update it.
     */
    public static <T> ICommonTraits<T> findTypeInfo(Class<T> type) {
        ICommonTraits<T> typeInfo = getTypeInfo(type);
        if (typeInfo == null) {
            if (typeInfoMap.containsKey(type))
                return null;
            typeInfo = (ICommonTraits<T>) TypeTraitsResolve.findTraits(type);
            typeInfoMap.put(type, typeInfo);
        }
        return typeInfo;
    }

}
