package net.bodz.bas.http.viz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.ITypeMapper;
import net.bodz.bas.c.type.NameConventionTypeMapper;
import net.bodz.bas.meta.codegen.IndexedTypeLoader;

/**
 * @see IHttpViewBuilder The indexed type.
 */
@IndexedTypeLoader(IHttpViewBuilder.class)
public class IndexedHttpViewBuilderFactory
        extends AbstractHttpViewBuilderFactory {

    private List<ITypeMapper> htmTmaps = new ArrayList<>();

    public IndexedHttpViewBuilderFactory() {
        htmTmaps.add(new NameConventionTypeMapper(null, "_htm", true));
        htmTmaps.add(new NameConventionTypeMapper(null, 1, ".htm", "_htm", true));
    }

    @Override
    protected void initialize() {
        for (IHttpViewBuilder<?> impl : ServiceLoader.load(IHttpViewBuilder.class))
            addViewBuilder(impl);
    }

    @Override
    protected Collection<ITypeMapper> getTypeMappers() {
        return htmTmaps;
    }

    private static IndexedHttpViewBuilderFactory instance = new IndexedHttpViewBuilderFactory();

    public static IndexedHttpViewBuilderFactory getInstance() {
        return instance;
    }

}
