package net.bodz.swt.viz;

import java.io.File;
import java.util.Date;

import net.bodz.bas.meta.source.ChainUsage;
import net.bodz.bas.meta.source.OverrideOption;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.viz.AbstractViewBuilderFactory;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.swt.viz.form.vbo.BooleanVbo;
import net.bodz.swt.viz.form.vbo.DateVbo;
import net.bodz.swt.viz.form.vbo.ExceptionVbo;
import net.bodz.swt.viz.form.vbo.FileVbo;
import net.bodz.swt.viz.form.vbo.NumberVbo;
import net.bodz.swt.viz.form.vbo.StringVbo;

public abstract class AbstractSwtViewBuilderFactory
        extends AbstractViewBuilderFactory {

    public AbstractSwtViewBuilderFactory() {
        setup();
    }

    @OverrideOption(chain = ChainUsage.MUST)
    protected void setup() {
        typeMap.put(boolean.class, new BooleanVbo());
        typeMap.put(byte[].class, new R_binary());

        typeMap.put(Boolean.class, new BooleanVbo());

        typeMap.put(Number.class, new NumberVbo());
        typeMap.put(String.class, new StringVbo());
        typeMap.put(Throwable.class, new ExceptionVbo());

        typeMap.put(Date.class, new DateVbo());
        typeMap.put(File.class, new FileVbo());
    }

    @Override
    public <T> void addViewBuilder(Class<T> type, IViewBuilder<? super T> viewBuilder) {
        if (!(viewBuilder instanceof ISwtViewBuilder<?>))
            throw new IllegalArgumentException(tr._("Not an SWT view builder: ") + viewBuilder);
        typeMap.put(type, viewBuilder);
    }

    @Override
    protected <T> ISwtViewBuilder<T> getViewBuilder(IRefEntry<? extends T> entry) {
        return (ISwtViewBuilder<T>) super.getViewBuilder(entry);
    }

}
