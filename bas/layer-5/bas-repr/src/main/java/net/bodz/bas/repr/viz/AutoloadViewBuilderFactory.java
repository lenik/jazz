package net.bodz.bas.repr.viz;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.c.type.ITypeMapper;
import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.c.type.TypeChain;
import net.bodz.bas.c.type.TypePoMap;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.t.set.QmiTaggedSet;
import net.bodz.bas.t.set.TaggedSet;

public abstract class AutoloadViewBuilderFactory
        extends AbstractViewBuilderFactory {

    static final Logger logger = LoggerFactory.getLogger(AutoloadViewBuilderFactory.class);

    private TypePoMap<TaggedSet<IViewBuilder<?>>> typeMap = new TypePoMap<>();

    @Override
    protected <T> ViewBuilderSet<T> findViewBuilders(Class<? extends T> clazz, String... features) {
        ViewBuilderSet<T> list = new ViewBuilderSet<>();
        for (Class<?> c : TypeChain.ancestors(clazz, Object.class)) {
            TaggedSet<IViewBuilder<?>> tset = typeMap.get(c);

            if (tset == null) {
                try {
                    IViewBuilder<?> loaded = autoload(c);
                    if (loaded != null)
                        addViewBuilder(loaded);
                } catch (ReflectiveOperationException e) {
                    logger.error(e, "Failed to autoload ", c);
                }
                // re-get the tagset after add.
                tset = getTaggedSet(c, false);
            }

            if (tset != null) {
                @SuppressWarnings("unchecked")
                Collection<? extends IViewBuilder<T>> selection //
                = (Collection<? extends IViewBuilder<T>>) (Object) tset.selectForAll(features);
                if (!selection.isEmpty()) {
                    list.addAll(selection);
                    break; // stop if vbo for any subclass exists.
                }
            }
        }
        return list;
    }

    protected synchronized TaggedSet<IViewBuilder<?>> getTaggedSet(Class<?> clazz, boolean autoCreate) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        TaggedSet<IViewBuilder<?>> tset = typeMap.get(clazz);
        if (tset == null) {
            if (autoCreate) {
                tset = new QmiTaggedSet<>();
                typeMap.put(clazz, tset);
            } else {
                // set = TaggedSet.fn.empty();
            }
        }
        return tset;
    }

    protected Collection<ITypeMapper> getTypeMappers() {
        return Collections.emptySet();
    }

    IViewBuilder<?> autoload(Class<?> clazz)
            throws ReflectiveOperationException {
        for (ITypeMapper tmap : getTypeMappers()) {
            Class<?> vboClass = tmap.map(clazz);
            if (vboClass != null) {
                IViewBuilder<?> vbo;
                // vbo = (IViewBuilder<?>) SingletonUtil.callGetInstance(vboClass);
                vbo = (IViewBuilder<?>) SingletonUtil.instantiateCached(vboClass);
                return vbo;
            }
        }
        return null;
    }

    @Override
    protected void addViewBuilder(IViewBuilder<?> viewBuilder, Class<?> clazz, List<String> tags) {
        checkViewBuilder(viewBuilder);
        TaggedSet<IViewBuilder<?>> set = getTaggedSet(clazz, true);
        for (String tag : tags)
            set.add(viewBuilder, tag);
    }

}
