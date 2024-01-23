package net.bodz.lily.entity.type;

import java.util.HashMap;
import java.util.Map;

public class EntityTypes {

    static Map<Class<?>, IEntityTypeInfo> cache = new HashMap<>();

    public static synchronized IEntityTypeInfo getTypeInfo(Class<?> entityClass) {
        IEntityTypeInfo typeInfo = cache.get(entityClass);
        if (typeInfo == null)
            synchronized (cache) {
                typeInfo = cache.get(entityClass);
                if (typeInfo == null) {
                    typeInfo = new DefaultEntityTypeInfo(entityClass);
                    cache.put(entityClass, typeInfo);
                }
            }
        return typeInfo;
    }

}
