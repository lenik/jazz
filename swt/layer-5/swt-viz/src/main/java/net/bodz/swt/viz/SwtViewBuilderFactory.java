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

public abstract class SwtViewBuilderFactory
        extends AbstractViewBuilderFactory {

    private static final long serialVersionUID = 4665944902525510516L;

    public SwtViewBuilderFactory() {
        setup();
    }

    @OverrideOption(chain = ChainUsage.MUST)
    protected void setup() {
        put(byte[].class, new R_binary());
        put(boolean.class, new BooleanVbo());
        put(Boolean.class, new BooleanVbo());
        put(Number.class, new NumberVbo());
        put(String.class, new StringVbo());
        put(Throwable.class, new ExceptionVbo());

        put(Date.class, new DateVbo());
        put(File.class, new FileVbo());
    }

    @Override
    public SwtViewBuilder<?> put(Class<?> key, IViewBuilder<?> value) {
        if (!(value instanceof SwtViewBuilder))
            throw new IllegalArgumentException(tr._("not a SWTRenderer: ") + value);
        return (SwtViewBuilder<?>) super.put(key, value);
    }

    @Override
    protected <T> SwtViewBuilder<T> getViewBuilder(IRefEntry<? extends T> entry) {
        return (SwtViewBuilder<T>) super.getViewBuilder(entry);
    }

}
