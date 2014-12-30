package net.bodz.bas.html;

import net.bodz.bas.repr.viz.AbstractViewBuilderFactory;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractHtmlViewBuilderFactory
        extends AbstractViewBuilderFactory
        implements IHtmlViewBuilderFactory {

    @Override
    public <T> IHtmlViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features) {
        return (IHtmlViewBuilder<T>) super.getViewBuilder(type);
    }

    @Override
    public <T> IHtmlViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features) {
        return (IHtmlViewBuilder<T>) super.getViewBuilder(ref);
    }

    @Override
    protected void checkViewBuilder(IViewBuilder<?> viewBuilder) {
        if (!(viewBuilder instanceof IHtmlViewBuilder<?>))
            throw new IllegalArgumentException("Not for HTML view: " + viewBuilder);
    }

}
