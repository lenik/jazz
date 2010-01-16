package net.bodz.bas.type;

import java.util.HashMap;
import java.util.Map;


/**
 * XXX - apply ehcache etc. to the static map.
 */
public class TypeTraitsCache {

    private static final Map<Class<?>, ITypeTraits> typeInfoMap;
    static {
        typeInfoMap = new HashMap<Class<?>, ITypeTraits>();
    }

    public static ITypeTraits getTypeInfo(Class<?> type) {
        return typeInfoMap.get(type);
    }

    public static void setTypeInfo(Class<?> type, ITypeTraits typeInfo) {
        typeInfoMap.put(type, typeInfo);
    }

    /**
     * Get type info from the cache, or update it.
     */
    public static ITypeTraits findTypeInfo(Class<?> type) {
        ITypeTraits typeInfo = typeInfoMap.get(type);
        if (typeInfo == null) {
            if (typeInfoMap.containsKey(type))
                return null;
            typeInfo = TypeTraitsResolve.findTraits(type);
            typeInfoMap.put(type, typeInfo);
        }
        return typeInfo;
    }

}
