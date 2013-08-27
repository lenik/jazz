package net.bodz.bas.tf.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.rtx.IQueryable;

public class TypeFeatureImplCache {

    static final Map<Class<?>, Object> typeFeatureImplCache;
    static {
        typeFeatureImplCache = new HashMap<Class<?>, Object>();
    }

    public static <T> T getTypeFeature(Class<?> typeFeatureImplType, Class<T> typeFeatureClass)
            throws ReflectiveOperationException {
        Object typeFeatureImpl = typeFeatureImplCache.get(typeFeatureImplType);
        if (typeFeatureImpl == null) {
            typeFeatureImpl = typeFeatureImplType.newInstance();
            typeFeatureImplCache.put(typeFeatureImplType, typeFeatureImpl);
        }

        if (IQueryable.class.isAssignableFrom(typeFeatureImplType)) {
            IQueryable querable = (IQueryable) typeFeatureImpl;
            T typeFeatures = querable.query(typeFeatureClass);
            return typeFeatures;

        } else {
            return typeFeatureClass.cast(typeFeatureImpl);
        }

    }

}
