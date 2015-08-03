package net.bodz.bas.http.viz;

import java.util.ServiceLoader;


/**
 * @see IHttpViewBuilder The indexed type.
 */
public class IndexedHttpViewBuilderFactory
        extends AbstractHttpViewBuilderFactory {

    @Override
    protected void initialize() {
        for (IHttpViewBuilder<?> impl : ServiceLoader.load(IHttpViewBuilder.class))
            addViewBuilder(impl);
    }

    private static IndexedHttpViewBuilderFactory instance = new IndexedHttpViewBuilderFactory();

    public static IndexedHttpViewBuilderFactory getInstance() {
        return instance;
    }

}
