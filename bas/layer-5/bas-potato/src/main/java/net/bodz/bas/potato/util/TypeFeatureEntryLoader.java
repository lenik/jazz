package net.bodz.bas.potato.util;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.tf.TypeFeatures;

public class TypeFeatureEntryLoader<T>
        implements IMapEntryLoader<Class<?>, T> {

    private final Class<T> typeFeatureClass;

    public TypeFeatureEntryLoader(Class<T> typeFeatureClass) {
        this.typeFeatureClass = typeFeatureClass;
    }

    @Override
    public T loadValue(Class<?> key)
            throws LazyLoadException {
        T typeFeature = TypeFeatures.getTypeFeature(key, typeFeatureClass);
        return typeFeature;
    }

    public static <T> TypeFeatureEntryLoader<T> forClass(Class<T> typeFeatureClass) {
        return new TypeFeatureEntryLoader<>(typeFeatureClass);
    }

}
