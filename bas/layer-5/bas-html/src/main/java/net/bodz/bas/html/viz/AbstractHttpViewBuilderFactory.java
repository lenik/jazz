package net.bodz.bas.html.viz;

import net.bodz.bas.repr.viz.AbstractViewBuilderFactory;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractHttpViewBuilderFactory
        extends AbstractViewBuilderFactory
        implements IHttpViewBuilderFactory {

    @Override
    public <T> IHttpViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features) {
        return (IHttpViewBuilder<T>) super.getViewBuilder(type);
    }

    @Override
    public <T> IHttpViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features) {
        return (IHttpViewBuilder<T>) super.getViewBuilder(ref);
    }

    @Override
    protected void checkViewBuilder(IViewBuilder<?> viewBuilder) {
        if (!(viewBuilder instanceof IHttpViewBuilder<?>))
            throw new IllegalArgumentException("Not for HTML view: " + viewBuilder);
    }

}
