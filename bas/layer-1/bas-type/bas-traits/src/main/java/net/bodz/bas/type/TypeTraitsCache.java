package net.bodz.bas.type;

import java.util.HashMap;
import java.util.Map;

/**
 * XXX - apply ehcache etc. to the static map.
 */
public class TypeTraitsCache {

    private static final Map<Class<?>, ITypeTraits<?>> typeInfoMap;
    static {
        typeInfoMap = new HashMap<Class<?>, ITypeTraits<?>>();
    }

    @SuppressWarnings("unchecked")
    public static <T> ITypeTraits<T> getTypeInfo(Class<T> type) {
        return (ITypeTraits<T>) typeInfoMap.get(type);
    }

    public static <T> void setTypeInfo(Class<T> type, ITypeTraits<T> typeInfo) {
        typeInfoMap.put(type, typeInfo);
    }

    /**
     * Get type info from the cache, or update it.
     */
    public static <T> ITypeTraits<T> findTypeInfo(Class<T> type) {
        ITypeTraits<T> typeInfo = getTypeInfo(type);
        if (typeInfo == null) {
            if (typeInfoMap.containsKey(type))
                return null;
            typeInfo = (ITypeTraits<T>) TypeTraitsResolve.findTraits(type);
            typeInfoMap.put(type, typeInfo);
        }
        return typeInfo;
    }

}
