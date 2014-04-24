package net.bodz.bas.html;

import net.bodz.bas.repr.viz.AbstractViewBuilderFactory;
import net.bodz.bas.repr.viz.IViewBuilder;

public abstract class AbstractHtmlViewBuilderFactory
        extends AbstractViewBuilderFactory
        implements IHtmlViewBuilderFactory {

    @Override
    public <T> IHtmlViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features) {
        return (IHtmlViewBuilder<T>) super.getViewBuilder(type);
    }

    @Override
    protected void checkViewBuilder(IViewBuilder<?> viewBuilder) {
        if (!(viewBuilder instanceof IHtmlViewBuilder<?>))
            throw new IllegalArgumentException("Not for HTML view: " + viewBuilder);
    }

}
