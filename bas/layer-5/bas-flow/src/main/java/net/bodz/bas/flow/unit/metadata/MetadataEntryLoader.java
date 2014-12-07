package net.bodz.bas.flow.unit.metadata;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.meta.stereo.MetadataClass;

public abstract class MetadataEntryLoader<T>
        implements IMapEntryLoader<Class<?>, T> {

    @Override
    public T loadValue(Class<?> clazz)
            throws LazyLoadException {
        T meta;
        Class<?> metaClass = Nullables.getAnnotation(clazz, MetadataClass.class).value();
        if (metaClass != null)
            try {
                meta = (T) metaClass.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new LazyLoadException("Can't create instance for MetaClass: " + metaClass, e);
            }
        else {
            meta = createWithoutAnnotation(clazz);
        }
        return meta;
    }

    protected abstract T createWithoutAnnotation(Class<?> clazz);

}
