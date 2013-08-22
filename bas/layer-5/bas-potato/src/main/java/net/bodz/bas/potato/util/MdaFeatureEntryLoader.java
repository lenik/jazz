package net.bodz.bas.potato.util;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.mf.MdaFeatures;

public class MdaFeatureEntryLoader<T>
        implements IMapEntryLoader<Class<?>, T> {

    private final Class<T> mdaFeatureClass;

    public MdaFeatureEntryLoader(Class<T> mdaFeatureClass) {
        this.mdaFeatureClass = mdaFeatureClass;
    }

    @Override
    public T loadValue(Class<?> key)
            throws LazyLoadException {
        T mdaFeature = MdaFeatures.getMdaFeature(key, mdaFeatureClass);
        return mdaFeature;
    }

    public static <T> MdaFeatureEntryLoader<T> forClass(Class<T> mdaFeatureClass) {
        return new MdaFeatureEntryLoader<>(mdaFeatureClass);
    }

}
