package net.bodz.bas.repr.viz;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.ref.IRefEntry;

public abstract class AbstractViewBuilderFactory
        implements IViewBuilderFactory, II18nCapable {

    protected TypePoMap<IViewBuilder<?>> typeMap = new TypePoMap<>();

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public <T> IViewBuilder<T> getViewBuilder(Type type, Annotation[] annotations) {
        Class<?> rawType;
        // if (type.getClass() == Class.class)
        if (type instanceof Class<?>)
            rawType = (Class<?>) type;
        else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            rawType = (Class<?>) pt.getRawType();
        } else
            throw new IllegalArgumentException("Unsupported type: " + type);

        return getViewBuilder((Class) rawType);
    }

    @Override
    public <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type) {
        Class<?> usingType = typeMap.floorKey(type);
        if (usingType == null) {
            if (type.isPrimitive()) {
                usingType = typeMap.floorKey(Primitives.box(type));
                if (usingType == null)
                    return null;
            } else
                return null;
        }
        IViewBuilder<T> viewBuilder = (IViewBuilder<T>) typeMap.get(usingType);
        return viewBuilder;
    }

    /**
     * @throws NullPointerException
     *             if var is null.
     * @return <code>null</code> if no matching renderer.
     */
    protected <T> IViewBuilder<T> getViewBuilder(IRefEntry<? extends T> entry) {
        if (entry == null)
            throw new NullPointerException("entry");
        Class<? extends T> type = (Class<T>) entry.getValueType();
        return getViewBuilder(type);
    }

    public <T> void addViewBuilder(Class<T> rawType, IViewBuilder<? super T> viewBuilder) {
        typeMap.put(rawType, viewBuilder);
    }

    public void addViewBuilder(Type type, Annotation[] annotation, IViewBuilder<?> viewBuilder) {
        Class<?> rawType;
        // if (type.getClass() == Class.class)
        if (type instanceof Class<?>)
            rawType = (Class<?>) type;
        else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            rawType = (Class<?>) pt.getRawType();
        } else
            throw new IllegalArgumentException("Unsupported type: " + type);

        typeMap.put(rawType, viewBuilder);
    }

}
