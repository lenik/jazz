package net.bodz.bas.pdf.viz;

import java.util.ServiceLoader;

import net.bodz.bas.http.viz.AbstractHttpViewBuilderFactory;
import net.bodz.bas.repr.viz.IViewBuilder;

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

    @Override
    protected void checkViewBuilder(IViewBuilder<?> viewBuilder) {
        if (!(viewBuilder instanceof IPdfViewBuilder<?>))
            throw new IllegalArgumentException("Not a PDF view builder: " + viewBuilder);
    }

    private static IndexedPdfViewBuilderFactory instance = new IndexedPdfViewBuilderFactory();

    public static IndexedPdfViewBuilderFactory getInstance() {
        return instance;
    }

}
