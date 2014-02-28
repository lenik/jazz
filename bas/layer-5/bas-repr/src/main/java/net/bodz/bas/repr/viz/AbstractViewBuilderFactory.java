package net.bodz.bas.repr.viz;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import net.bodz.bas.c.object.IEmptyConsts;
import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.rtx.IOptions;

public abstract class AbstractViewBuilderFactory
        implements IViewBuilderFactory, II18nCapable {

    protected TypePoMap<IViewBuilder<?>> typeMap = new TypePoMap<>();

    @Override
    public String[] getSupportedFeatures() {
        return IEmptyConsts.emptyStringArray;
    }

    @Override
    public <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features) {
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
        Class<? extends T> type = entry.getValueType();
        return getViewBuilder(type);
    }

    protected <T> void addViewBuilder(Class<?> rawType, IViewBuilder<?> viewBuilder) {
        typeMap.put(rawType, viewBuilder);
    }

    protected void addViewBuilder(Type type, Annotation[] annotation, IViewBuilder<?> viewBuilder) {
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

    @Override
    public Object buildView(Object ctx, IRefEntry<?> entry)
            throws ViewBuilderException {
        return buildView(ctx, entry, IOptions.NULL);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object buildView(Object ctx, IRefEntry<?> entry, IOptions options)
            throws ViewBuilderException {
        Class<?> valueType = entry.getValueType();
        IViewBuilder viewBuilder = getViewBuilder(valueType);
        Object view = viewBuilder.buildView(ctx, entry, options);
        return view;
    }

}
