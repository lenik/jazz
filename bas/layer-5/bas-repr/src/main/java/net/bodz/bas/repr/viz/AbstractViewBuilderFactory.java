package net.bodz.bas.repr.viz;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import net.bodz.bas.c.object.IEmptyConsts;
import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.c.type.TypeChain;
import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.view.Feature;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.set.QmiTaggedSet;
import net.bodz.bas.t.set.TaggedSet;

public abstract class AbstractViewBuilderFactory
        implements IViewBuilderFactory, II18nCapable {

    private TypePoMap<TaggedSet<IViewBuilder<?>>> typeMap = new TypePoMap<>();

    @Override
    public String[] getSupportedFeatures() {
        return IEmptyConsts.emptyStringArray;
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

    /**
     * @throws NullPointerException
     *             if var is null.
     * @return <code>null</code> if no matching renderer.
     */
    protected/* final */<T> IViewBuilder<T> getViewBuilder(IRefEntry<? extends T> entry) {
        if (entry == null)
            throw new NullPointerException("entry");
        Class<? extends T> type = entry.getValueType();

        String[] features = {};
        Feature _feature = entry.getAnnotation(Feature.class);
        if (_feature != null)
            features = _feature.value();

        return getViewBuilder(type, features);
    }

    @Override
    public <T> IViewBuilder<T> getViewBuilder(Class<? extends T> clazz, String... features) {
        for (Class<?> c : TypeChain.ancestors(clazz, Object.class)) {
            TaggedSet<IViewBuilder<?>> set = typeMap.get(c);

            if (set == null) {
                // auto load...
                try {
                    IViewBuilder<?> friendVbo = findFriendVbo(c);
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

    /**
     * @throws ReflectiveOperationException
     * @throws LinkageError
     */
    IViewBuilder<?> findFriendVbo(Class<?> clazz)
            throws ReflectiveOperationException {
        String vboFqcn;

        String simpleName = clazz.getSimpleName();
        if (clazz.isInterface() //
                && simpleName.charAt(0) == 'I' //
                && simpleName.length() > 1 //
                && Character.isUpperCase(simpleName.charAt(1))) {
            String unprefix = clazz.getPackage().getName() + "." + simpleName.substring(1);
            vboFqcn = unprefix + "Vbo";
        } else {
            String fqcn = clazz.getName();
            vboFqcn = fqcn + "Vbo";
        }

        Class<?> vboClass;
        try {
            vboClass = Class.forName(vboFqcn);
        } catch (ClassNotFoundException e) {
            return null;
        }

        IViewBuilder<?> vbo;
        // vbo = (IViewBuilder<?>) SingletonUtil.callGetInstance(vboClass);
        vbo = (IViewBuilder<?>) SingletonUtil.instantiateCached(vboClass);
        return vbo;
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
        checkViewBuilder(viewBuilder);
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
