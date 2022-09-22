package net.bodz.bas.repr.viz.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.IClassFunction;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;

/**
 * @see IHttpViewBuilder The indexed type.
 */
@IndexedTypeLoader(IHttpViewBuilder.class)
public class IndexedHttpViewBuilderFactory
        extends AbstractHttpViewBuilderFactory {

    private List<IClassFunction> viewClassResolvers = new ArrayList<>();

    public IndexedHttpViewBuilderFactory() {
        viewClassResolvers.add(NameConventions.foo_bar_htm);
    }

    public void registerViewClassFn(IClassFunction viewClassFn) {
        if (viewClassFn == null)
            throw new NullPointerException("viewClassFn");
        viewClassResolvers.add(viewClassFn);
    }

    @Override
    protected void initialize() {
        for (IHttpViewBuilder<?> impl : ServiceLoader.load(IHttpViewBuilder.class))
            addViewBuilder(impl);
    }

    @Override
    protected Collection<IClassFunction> getViewClassResolvers() {
        return viewClassResolvers;
    }

    private static IndexedHttpViewBuilderFactory instance = new IndexedHttpViewBuilderFactory();

    public static IndexedHttpViewBuilderFactory getInstance() {
        return instance;
    }

}
