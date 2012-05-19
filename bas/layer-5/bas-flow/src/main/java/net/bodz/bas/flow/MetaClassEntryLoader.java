package net.bodz.bas.flow;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.meta.stereo.MetaClass;
import net.bodz.bas.util.Nullables;

public abstract class MetaClassEntryLoader<T>
        implements IMapEntryLoader<Class<?>, T> {

    @Override
    public T loadEntry(Class<?> clazz)
            throws LazyLoadException {
        T meta;
        Class<?> metaClass = Nullables.getAnnotation(clazz, MetaClass.class).value();
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
