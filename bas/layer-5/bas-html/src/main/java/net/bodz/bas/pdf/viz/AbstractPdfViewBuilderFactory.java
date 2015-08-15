package net.bodz.bas.pdf.viz;

import net.bodz.bas.repr.viz.AutoloadViewBuilderFactory;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractPdfViewBuilderFactory
        extends AutoloadViewBuilderFactory
        implements IPdfViewBuilderFactory {

    @Override
    public <T> IPdfViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features) {
        return (IPdfViewBuilder<T>) super.getViewBuilder(type, features);
    }

    @Override
    public <T> IPdfViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features) {
        return (IPdfViewBuilder<T>) super.getViewBuilder(ref, features);
    }

    @Override
    protected void checkViewBuilder(IViewBuilder<?> viewBuilder) {
        if (!(viewBuilder instanceof IPdfViewBuilder<?>))
            throw new IllegalArgumentException("Not for Pdf view: " + viewBuilder);
    }

}
