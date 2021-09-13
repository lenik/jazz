package net.bodz.bas.repr.viz.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.ITypeMapper;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;

/**
 * @see IHttpViewBuilder The indexed type.
 */
@IndexedTypeLoader(IHttpViewBuilder.class)
public class IndexedHttpViewBuilderFactory
        extends AbstractHttpViewBuilderFactory {

    private List<ITypeMapper> viewMappers = new ArrayList<ITypeMapper>();

    public IndexedHttpViewBuilderFactory() {
        viewMappers.add(NameConventions.foo_bar_htm);
    }

    public void registerViewMapper(ITypeMapper viewMapper) {
        if (viewMapper == null)
            throw new NullPointerException("viewMapper");
        viewMappers.add(viewMapper);
    }

    @Override
    protected void initialize() {
        for (IHttpViewBuilder<?> impl : ServiceLoader.load(IHttpViewBuilder.class))
            addViewBuilder(impl);
    }

    @Override
    protected Collection<ITypeMapper> getTypeMappers() {
        return viewMappers;
    }

    private static IndexedHttpViewBuilderFactory instance = new IndexedHttpViewBuilderFactory();

    public static IndexedHttpViewBuilderFactory getInstance() {
        return instance;
    }

}
