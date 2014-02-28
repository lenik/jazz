package net.bodz.bas.repr.html;

import net.bodz.bas.repr.viz.AbstractViewBuilderFactory;

public abstract class AbstractHtmlViewBuilderFactory
        extends AbstractViewBuilderFactory
        implements IHtmlViewBuilderFactory {

    @Override
    public <T> IHtmlViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features) {
        return (IHtmlViewBuilder<T>) super.getViewBuilder(type);
    }

}
