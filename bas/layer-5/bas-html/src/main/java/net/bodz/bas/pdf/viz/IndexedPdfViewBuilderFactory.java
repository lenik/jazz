package net.bodz.bas.pdf.viz;

import java.util.ServiceLoader;

import net.bodz.bas.http.viz.AbstractHttpViewBuilderFactory;

/**
 * @see IPdfViewBuilder The indexed type.
 */
public class IndexedPdfViewBuilderFactory
        extends AbstractHttpViewBuilderFactory {

    @Override
    protected void initialize() {
        for (IPdfViewBuilder<?> impl : ServiceLoader.load(IPdfViewBuilder.class))
            addViewBuilder(impl);
    }

    private static IndexedPdfViewBuilderFactory instance = new IndexedPdfViewBuilderFactory();

    public static IndexedPdfViewBuilderFactory getInstance() {
        return instance;
    }

}
