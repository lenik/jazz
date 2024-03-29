package net.bodz.bas.flow.unit.metadata;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.meta.stereo.MetadataClass;

public abstract class MetadataEntryLoader<T>
        implements
            IMapEntryLoader<Class<?>, T> {

    @SuppressWarnings("unchecked")
    @Override
    public T loadValue(Class<?> clazz)
            throws LazyLoadException {
        T meta;
        MetadataClass aMetadataClass = clazz.getAnnotation(MetadataClass.class);
        if (aMetadataClass != null) {
            Class<?> metaClass = aMetadataClass.value();
            try {
                meta = (T) metaClass.getConstructor().newInstance();
            } catch (ReflectiveOperationException e) {
                throw new LazyLoadException("Can't create instance for MetaClass: " + metaClass, e);
            }
        } else {
            meta = createWithoutAnnotation(clazz);
        }
        return meta;
    }

    protected abstract T createWithoutAnnotation(Class<?> clazz);

}
