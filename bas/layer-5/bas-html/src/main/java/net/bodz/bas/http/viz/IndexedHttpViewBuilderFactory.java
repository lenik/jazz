package net.bodz.bas.http.viz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.c.type.TypeNearby;

/**
 * @see IHttpViewBuilder The indexed type.
 */
public class IndexedHttpViewBuilderFactory
        extends AbstractHttpViewBuilderFactory {

    private List<TypeNearby> typeNearbies = new ArrayList<TypeNearby>();

    public IndexedHttpViewBuilderFactory() {
        typeNearbies.add(new TypeNearby(null, null, "_htm", true));
    }

    @Override
    protected void initialize() {
        for (IHttpViewBuilder<?> impl : ServiceLoader.load(IHttpViewBuilder.class))
            addViewBuilder(impl);
    }

    @Override
    protected Collection<TypeNearby> getAutoloadSpecs() {
        return typeNearbies;
    }

    private static IndexedHttpViewBuilderFactory instance = new IndexedHttpViewBuilderFactory();

    public static IndexedHttpViewBuilderFactory getInstance() {
        return instance;
    }

}
