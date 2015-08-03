package net.bodz.bas.html.viz;

import java.util.ServiceLoader;

/**
 * @see IHtmlViewBuilder The indexed type.
 */
public class IndexedHtmlViewBuilderFactory
        extends AbstractHtmlViewBuilderFactory {

    @Override
    protected void initialize() {
        for (IHtmlViewBuilder<?> impl : ServiceLoader.load(IHtmlViewBuilder.class))
            addViewBuilder(impl);
    }

    private static IndexedHtmlViewBuilderFactory instance = new IndexedHtmlViewBuilderFactory();

    public static IndexedHtmlViewBuilderFactory getInstance() {
        return instance;
    }

}
