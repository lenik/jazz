package net.bodz.bas.type.traits.impl;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.type.ITypeTraits;


/**
 * XXX - apply ehcache etc. to the static map.
 */
public class TypeInfoResolveCache {

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
            typeInfo = TypeInfoResolve.findTypeInfo(type);
            typeInfoMap.put(type, typeInfo);
        }
        return typeInfo;
    }

}
