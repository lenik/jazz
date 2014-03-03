package net.bodz.swt.viz;

import net.bodz.bas.meta.source.ChainUsage;
import net.bodz.bas.meta.source.OverrideOption;
import net.bodz.bas.repr.viz.AbstractViewBuilderFactory;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.swt.viz.form.vbo.BooleanVbo;
import net.bodz.swt.viz.form.vbo.DateVbo;
import net.bodz.swt.viz.form.vbo.ExceptionVbo;
import net.bodz.swt.viz.form.vbo.FileVbo;
import net.bodz.swt.viz.form.vbo.StringVbo;
import net.bodz.swt.viz.form.vbo.TextFormedVbo;

public abstract class AbstractSwtViewBuilderFactory
        extends AbstractViewBuilderFactory
        implements ISwtViewBuilderFactory {

    public AbstractSwtViewBuilderFactory() {
        setup();
    }

    @OverrideOption(chain = ChainUsage.MUST)
    protected void setup() {
        addViewBuilder(new BooleanVbo());

        addViewBuilder(new TextFormedVbo(), Number.class);
        addViewBuilder(new StringVbo());
        addViewBuilder(new ExceptionVbo());

        addViewBuilder(new DateVbo());
        addViewBuilder(new FileVbo());
    }

    @Override
    public <T> ISwtViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features) {
        return (ISwtViewBuilder<T>) super.getViewBuilder(type);
    }

    protected void checkViewBuilder(IViewBuilder<?> viewBuilder) {
        if (!(viewBuilder instanceof ISwtViewBuilder<?>))
            throw new IllegalArgumentException(tr._("Not an SWT view builder: ") + viewBuilder);
    }

}
