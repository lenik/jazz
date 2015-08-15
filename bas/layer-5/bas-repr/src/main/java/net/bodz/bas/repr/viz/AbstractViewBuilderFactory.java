package net.bodz.bas.repr.viz;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.view.Feature;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractViewBuilderFactory
        implements IViewBuilderFactory, II18nCapable {

    static final Logger logger = LoggerFactory.getLogger(AbstractViewBuilderFactory.class);

    private boolean initialized;

    private Map<TaggedType, Object> viewBuilderCache;

    public AbstractViewBuilderFactory() {
        viewBuilderCache = new HashMap<>();
    }

    protected void lazyInit() {
        if (!initialized)
            synchronized (this) {
                if (!initialized) {
                    initialize();
                    initialized = true;
                }
            }
    }

    protected abstract void initialize();

    protected abstract <T> ViewBuilderSet<T> findViewBuilders(Class<? extends T> clazz, String... features);

    @Override
    public <T> ViewBuilderSet<T> getViewBuilders(IUiRef<? extends T> ref, String... features) {
        Class<? extends T> valueType = ref.getValueType();
        return getViewBuilders(valueType, features);
    }

    @Override
    public <T> ViewBuilderSet<T> getViewBuilders(Class<? extends T> clazz, String... features) {
        if (clazz == null)
            throw new NullPointerException("clazz");

        lazyInit();

        if (clazz.isPrimitive())
            clazz = (Class<? extends T>) Primitives.box(clazz);

        TaggedType key = new TaggedType(clazz, features);
        Object cache = viewBuilderCache.get(key);
        if (cache == null) {
            ViewBuilderSet<T> list = findViewBuilders(clazz, features);
            viewBuilderCache.put(key, cache = list);
        }

        @SuppressWarnings("unchecked")
        ViewBuilderSet<T> list = (ViewBuilderSet<T>) cache;
        return list;
    }

    protected <T> IViewBuilder<T> getViewBuilder(IRefEntry<? extends T> entry) {
        if (entry == null)
            throw new NullPointerException("entry");
        Class<? extends T> valueType = entry.getValueType();

        String[] features = {};
        Feature _feature = entry.getAnnotation(Feature.class);
        if (_feature != null)
            features = _feature.value();

        return getViewBuilder(valueType, features);
    }

    @Override
    public <T> IViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features) {
        ViewBuilderSet<T> set = getViewBuilders(ref, features);
        return set.isEmpty() ? null : set.getAny();
    }

    @Override
    public <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features) {
        ViewBuilderSet<T> set = getViewBuilders(type, features);
        return set.isEmpty() ? null : set.getAny();
    }

    protected final void addViewBuilder(IViewBuilder<?> viewBuilder) {
        checkViewBuilder(viewBuilder);
        addViewBuilder(viewBuilder, viewBuilder.getValueType(), viewBuilder.getSupportedFeatures());
    }

    protected abstract void addViewBuilder(IViewBuilder<?> viewBuilder, Class<?> clazz, String... features);

    protected void addViewBuilder(IViewBuilder<?> viewBuilder, Type type, Annotation[] annotation, String... features) {
        checkViewBuilder(viewBuilder);
        Class<?> rawType;
        // if (type.getClass() == Class.class)
        if (type instanceof Class<?>)
            rawType = (Class<?>) type;
        else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            rawType = (Class<?>) pt.getRawType();
        } else
            throw new IllegalArgumentException("Unsupported type: " + type);

        addViewBuilder(viewBuilder, rawType, features);
    }

    protected void checkViewBuilder(IViewBuilder<?> viewBuilder) {
        if (viewBuilder == null)
            throw new NullPointerException("viewBuilder");
    }

    @Override
    public final Object buildView(IQueryable ctx, Object parent, IUiRef<?> ref)
            throws ViewBuilderException {
        return buildView(ctx, parent, ref, IOptions.NULL);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object buildView(IQueryable ctx, Object parent, IUiRef<?> ref, IOptions options)
            throws ViewBuilderException {
        Class<?> valueType = ref.getValueType();
        IViewBuilder viewBuilder = getViewBuilder(valueType);
        Object view = viewBuilder.buildView(ctx, parent, ref, options);
        return view;
    }

}
