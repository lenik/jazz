package net.bodz.swt.viz;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

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
        typeMap.put(boolean.class, new BooleanVbo());
        // typeMap.put(byte[].class, new ByteArrayVbo());

        typeMap.put(Boolean.class, new BooleanVbo());

        typeMap.put(Number.class, new TextFormedVbo());
        typeMap.put(String.class, new StringVbo());
        typeMap.put(Throwable.class, new ExceptionVbo());

        typeMap.put(Date.class, new DateVbo());
        typeMap.put(File.class, new FileVbo());
    }

    @Override
    public <T> ISwtViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features) {
        return (ISwtViewBuilder<T>) super.getViewBuilder(type);
    }

    @Override
    protected <T> void addViewBuilder(Class<?> type, IViewBuilder<?> viewBuilder) {
        if (!(viewBuilder instanceof ISwtViewBuilder<?>))
            throw new IllegalArgumentException(tr._("Not an SWT view builder: ") + viewBuilder);
        typeMap.put(type, viewBuilder);
    }

    @Override
    protected final void addViewBuilder(Type type, Annotation[] annotation, IViewBuilder<?> viewBuilder) {
        if (!(viewBuilder instanceof ISwtViewBuilder<?>))
            throw new IllegalArgumentException("viewBuilder isn't swt capable.");
        else
            super.addViewBuilder(type, annotation, viewBuilder);
    }

}
