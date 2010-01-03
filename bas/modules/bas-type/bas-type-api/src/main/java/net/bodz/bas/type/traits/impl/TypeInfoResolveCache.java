package net.bodz.bas.type.traits.impl;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.type.traits.ITypeInfo;


/**
 * XXX - apply ehcache etc. to the static map.
 */
public class TypeInfoResolveCache {

    private static final Map<Class<?>, ITypeInfo> typeInfoMap;
    static {
        typeInfoMap = new HashMap<Class<?>, ITypeInfo>();
    }

    public static ITypeInfo getTypeInfo(Class<?> type) {
        return typeInfoMap.get(type);
    }

    public static void setTypeInfo(Class<?> type, ITypeInfo typeInfo) {
        typeInfoMap.put(type, typeInfo);
    }

    /**
     * Get type info from the cache, or update it.
     */
    public static ITypeInfo findTypeInfo(Class<?> type) {
        ITypeInfo typeInfo = typeInfoMap.get(type);
        if (typeInfo == null) {
            if (typeInfoMap.containsKey(type))
                return null;
            typeInfo = TypeInfoResolve.findTypeInfo(type);
            typeInfoMap.put(type, typeInfo);
        }
        return typeInfo;
    }

}
