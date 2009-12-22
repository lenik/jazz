package net.bodz.bas.typeinfo.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.typeinfo.TypeInfo;

/**
 * XXX - apply ehcache etc. to the static map.
 */
public class TypeInfoCache {

    private static final Map<Class<?>, TypeInfo> typeInfoMap;
    static {
        typeInfoMap = new HashMap<Class<?>, TypeInfo>();
    }

    public static TypeInfo getTypeInfo(Class<?> type) {
        return typeInfoMap.get(type);
    }

    public static void setTypeInfo(Class<?> type, TypeInfo typeInfo) {
        typeInfoMap.put(type, typeInfo);
    }

    /**
     * Get type info from the cache, or update it.
     */
    public static TypeInfo findTypeInfo(Class<?> type) {
        TypeInfo typeInfo = typeInfoMap.get(type);
        if (typeInfo == null) {
            if (typeInfoMap.containsKey(type))
                return null;
            typeInfo = TypeInfoUtil.findTypeInfo(type);
            typeInfoMap.put(type, typeInfo);
        }
        return typeInfo;
    }

}
