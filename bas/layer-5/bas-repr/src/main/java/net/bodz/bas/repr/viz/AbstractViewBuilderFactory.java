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

    protected abstract <T> ViewBuilderSet<T> findViewBuilders(Class<? extends T> clazz, String... tags);

    @Override
    public <T> ViewBuilderSet<T> getViewBuilders(IUiRef<? extends T> ref, String... tags) {
        Class<? extends T> valueType = ref.getValueType();
        return getViewBuilders(valueType, tags);
    }

    @Override
    public <T> ViewBuilderSet<T> getViewBuilders(Class<? extends T> clazz, String... tags) {
        if (clazz == null)
            throw new NullPointerException("clazz");

        lazyInit();

        if (clazz.isPrimitive())
            clazz = (Class<? extends T>) Primitives.box(clazz);

        TaggedType key = new TaggedType(clazz, tags);
        Object cache = viewBuilderCache.get(key);
        if (cache == null) {
            ViewBuilderSet<T> list = findViewBuilders(clazz, tags);
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

        String[] tags = {};
        Feature _feature = entry.getAnnotation(Feature.class);
        if (_feature != null)
            tags = _feature.value();

        return getViewBuilder(valueType, tags);
    }

    @Override
    public <T> IViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... tags) {
        ViewBuilderSet<T> set = getViewBuilders(ref, tags);
        return set.isEmpty() ? null : set.getAny();
    }

    @Override
    public <T> IViewBuilder<T> getViewBuilder(Class<? extends T> type, String... tags) {
        ViewBuilderSet<T> set = getViewBuilders(type, tags);
        return set.isEmpty() ? null : set.getAny();
    }

    protected final void addViewBuilder(IViewBuilder<?> viewBuilder) {
        checkViewBuilder(viewBuilder);
        addViewBuilder(viewBuilder, viewBuilder.getValueType(), tags);
    }

    protected abstract void addViewBuilder(IViewBuilder<?> viewBuilder, Class<?> clazz, String... tags);

    protected void addViewBuilder(IViewBuilder<?> viewBuilder, Type type, Annotation[] annotation, String... tags) {
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

        addViewBuilder(viewBuilder, rawType, tags);
    }

    protected void checkViewBuilder(IViewBuilder<?> viewBuilder) {
        if (viewBuilder == null)
            throw new NullPointerException("viewBuilder");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object buildView(IQueryable ctx, Object parent, IUiRef<?> ref)
            throws ViewBuilderException {
        Class<?> valueType = ref.getValueType();
        IViewBuilder viewBuilder = getViewBuilder(valueType);
        Object view = viewBuilder.buildViewStart(ctx, parent, ref);
        view = viewBuilder.buildViewEnd(ctx, view, ref);
        return view;
    }

}
