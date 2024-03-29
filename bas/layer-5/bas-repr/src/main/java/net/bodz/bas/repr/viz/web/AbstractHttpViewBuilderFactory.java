package net.bodz.bas.repr.viz.web;

import net.bodz.bas.repr.viz.AutoloadViewBuilderFactory;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractHttpViewBuilderFactory
        extends AutoloadViewBuilderFactory
        implements IHttpViewBuilderFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T> IHttpViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features) {
        return (IHttpViewBuilder<T>) super.getViewBuilder(type, features);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> IHttpViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features) {
        return (IHttpViewBuilder<T>) super.getViewBuilder(ref, features);
    }

    @Override
    protected void checkViewBuilder(IViewBuilder<?> viewBuilder) {
        if (!(viewBuilder instanceof IHttpViewBuilder<?>))
            throw new IllegalArgumentException("Not an HTTP view builder: " + viewBuilder);
    }

}
