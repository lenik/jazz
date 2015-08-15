package net.bodz.swt.viz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.c.type.TypeNearby;
import net.bodz.bas.repr.viz.AutoloadViewBuilderFactory;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.swt.viz.form.vbo.BooleanVbo;
import net.bodz.swt.viz.form.vbo.DateVbo;
import net.bodz.swt.viz.form.vbo.ExceptionVbo;
import net.bodz.swt.viz.form.vbo.FileVbo;
import net.bodz.swt.viz.form.vbo.StringVbo;

public abstract class AbstractSwtViewBuilderFactory
        extends AutoloadViewBuilderFactory
        implements ISwtViewBuilderFactory {

    private List<TypeNearby> typeNearbies = new ArrayList<TypeNearby>();

    public AbstractSwtViewBuilderFactory() {
        typeNearbies.add(new TypeNearby(null, null, "Vbo", true));
    }

    @Override
    protected void initialize() {
        addViewBuilder(new BooleanVbo());

        // TODO addViewBuilder(new TextFormedVbo(), Number.class);
        addViewBuilder(new StringVbo());
        addViewBuilder(new ExceptionVbo());

        addViewBuilder(new DateVbo());
        addViewBuilder(new FileVbo());
    }

    @Override
    protected Collection<TypeNearby> getAutoloadSpecs() {
        return typeNearbies;
    }

    @Override
    public <T> ISwtViewBuilder<T> getViewBuilder(Class<? extends T> type, String... features) {
        return (ISwtViewBuilder<T>) super.getViewBuilder(type);
    }

    @Override
    public <T> ISwtViewBuilder<T> getViewBuilder(IUiRef<? extends T> ref, String... features) {
        return (ISwtViewBuilder<T>) super.getViewBuilder(ref);
    }

    protected void checkViewBuilder(IViewBuilder<?> viewBuilder) {
        if (!(viewBuilder instanceof ISwtViewBuilder<?>))
            throw new IllegalArgumentException(tr._("Not an SWT view builder: ") + viewBuilder);
    }

}
