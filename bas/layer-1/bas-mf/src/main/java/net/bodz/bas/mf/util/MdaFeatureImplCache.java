package net.bodz.bas.mf.util;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.rtx.IQueryable;

public class MdaFeatureImplCache {

    static final Map<Class<?>, Object> mdaFeatureImplCache;
    static {
        mdaFeatureImplCache = new HashMap<Class<?>, Object>();
    }

    public static <T> T getMdaFeature(Class<?> mdaFeatureImplType, Class<T> mdaFeatureType)
            throws ReflectiveOperationException {
        Object mdaFeatureImpl = mdaFeatureImplCache.get(mdaFeatureImplType);
        if (mdaFeatureImpl == null) {
            mdaFeatureImpl = mdaFeatureImplType.newInstance();
            mdaFeatureImplCache.put(mdaFeatureImplType, mdaFeatureImpl);
        }

        if (IQueryable.class.isAssignableFrom(mdaFeatureImplType)) {
            IQueryable querable = (IQueryable) mdaFeatureImpl;
            T mdaFeatures = querable.query(mdaFeatureType);
            return mdaFeatures;

        } else {
            return mdaFeatureType.cast(mdaFeatureImpl);
        }

    }

}
