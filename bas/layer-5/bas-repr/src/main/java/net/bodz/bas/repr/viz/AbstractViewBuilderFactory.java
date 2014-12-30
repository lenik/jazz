package net.bodz.bas.repr.viz;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.object.IEmptyConsts;
import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.c.type.TypeChain;
import net.bodz.bas.c.type.TypeNearby;
import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.view.Feature;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.set.QmiTaggedSet;
import net.bodz.bas.t.set.TaggedSet;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractViewBuilderFactory
        implements IViewBuilderFactory, II18nCapable {

    private TypePoMap<TaggedSet<IViewBuilder<?>>> typeMap = new TypePoMap<>();

    private boolean initialized;
    private Map<FeaturedType, Object> viewBuilderCache;
    private static final Object NONE = new Object();

    public AbstractViewBuilderFactory() {
        viewBuilderCache = new HashMap<>();
    }

    protected abstract void initialize();

    @Override
    public String[] getSupportedFeatures() {
        return IEmptyConsts.emptyStringArray;
    }

    protected/* final */<T> IViewBuilder<T> getViewBuilder(IRefEntry<? extends T> entry) {
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
        Class<? extends T> valueType = ref.getValueType();
        return getViewBuilder(valueType, features);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> IViewBuilder<T> getViewBuilder(Class<? extends T> clazz, String... features) {
        if (clazz == null)
            throw new NullPointerException("clazz");

        if (!initialized)
            synchronized (this) {
                if (!initialized) {
                    initialize();
                    initialized = true;
                }
            }

        FeaturedType key = new FeaturedType(clazz, features);
        Object cache = viewBuilderCache.get(key);
        if (cache == null) {
            cache = findViewBuilder(clazz, features);
            viewBuilderCache.put(key, cache != null ? cache : NONE);
        }
        if (cache == NONE)
            return null;
        else
            return (IViewBuilder<T>) cache;
    }

    <T> IViewBuilder<T> findViewBuilder(Class<? extends T> clazz, String... features) {
        for (Class<?> c : TypeChain.ancestors(clazz, Object.class)) {
            TaggedSet<IViewBuilder<?>> set = typeMap.get(c);

            if (set == null) {
                // auto load...
                try {
                    IViewBuilder<?> friendVbo = findNearbyVbo(c);
                    if (friendVbo != null)
                        addViewBuilder(friendVbo);
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }

            set = getTaggedSet(c, false);
            if (set != null) {
                Collection<IViewBuilder<?>> selection = set.select(features);
                if (!selection.isEmpty()) {
                    IViewBuilder<T> first = (IViewBuilder<T>) selection.iterator().next();
                    return first;
                }
            }
        }
        return null;
    }

    protected synchronized TaggedSet<IViewBuilder<?>> getTaggedSet(Class<?> clazz, boolean autoCreate) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        TaggedSet<IViewBuilder<?>> set = typeMap.get(clazz);
        if (set == null) {
            if (autoCreate) {
                set = new QmiTaggedSet<>();
                typeMap.put(clazz, set);
            } else {
                // set = TaggedSet.fn.empty();
            }
        }
        return set;
    }

    TypeNearby[] nearbies = {
            //
            new TypeNearby(null, "Vbo", true), //
            new TypeNearby("impl", "Vbo", true) };

    /**
     * @throws ReflectiveOperationException
     * @throws LinkageError
     */
    IViewBuilder<?> findNearbyVbo(Class<?> clazz)
            throws ReflectiveOperationException {
        for (TypeNearby nearby : nearbies) {
            Class<?> vboClass = nearby.find(clazz);
            if (vboClass != null) {
                IViewBuilder<?> vbo;
                // vbo = (IViewBuilder<?>) SingletonUtil.callGetInstance(vboClass);
                vbo = (IViewBuilder<?>) SingletonUtil.instantiateCached(vboClass);
                return vbo;
            }
        }
        return null;
    }

    protected void addViewBuilder(IViewBuilder<?> viewBuilder) {
        checkViewBuilder(viewBuilder);
        addViewBuilder(viewBuilder, viewBuilder.getValueType(), getSupportedFeatures());
    }

    protected void addViewBuilder(IViewBuilder<?> viewBuilder, Class<?> clazz, String... features) {
        checkViewBuilder(viewBuilder);
        TaggedSet<IViewBuilder<?>> set = getTaggedSet(clazz, true);
        set.add(viewBuilder, features);
    }

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
    public Object buildView(Object ctx, Object parent, IUiRef<?> ref)
            throws ViewBuilderException {
        return buildView(ctx, parent, ref, IOptions.NULL);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object buildView(Object ctx, Object parent, IUiRef<?> ref, IOptions options)
            throws ViewBuilderException {
        Class<?> valueType = ref.getValueType();
        IViewBuilder viewBuilder = getViewBuilder(valueType);
        Object view = viewBuilder.buildView(ctx, parent, ref, options);
        return view;
    }

}
