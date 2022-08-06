package net.bodz.bas.repr.viz.web;

import net.bodz.bas.repr.viz.AbstractViewBuilder;

public abstract class AbstractHttpViewBuilder<T>
        extends AbstractViewBuilder<T>
        implements
            IHttpViewBuilder<T> {

    public AbstractHttpViewBuilder() {
        super();
    }

    public AbstractHttpViewBuilder(Class<?> valueClass) {
        super(valueClass);
    }

}
