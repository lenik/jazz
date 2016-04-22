package net.bodz.swt.viz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.c.type.ITypeMapper;
import net.bodz.bas.c.type.NameConventionTypeMapper;
import net.bodz.bas.repr.viz.AutoloadViewBuilderFactory;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.ui.dom1.IUiRef;
import net.bodz.swt.viz.form.vbo.Boolean_swt;
import net.bodz.swt.viz.form.vbo.Date_swt;
import net.bodz.swt.viz.form.vbo.Exception_swt;
import net.bodz.swt.viz.form.vbo.File_swt;
import net.bodz.swt.viz.form.vbo.String_swt;

public abstract class AbstractSwtViewBuilderFactory
        extends AutoloadViewBuilderFactory
        implements ISwtViewBuilderFactory {

    private List<ITypeMapper> swtTmaps = new ArrayList<>();

    public AbstractSwtViewBuilderFactory() {
        swtTmaps.add(new NameConventionTypeMapper(null, null, "_swt", true));
    }

    @Override
    protected void initialize() {
        addViewBuilder(new Boolean_swt());

         // TODO addViewBuilder(new TextFormedVbo(), Number.class);
        addViewBuilder(new String_swt());
        addViewBuilder(new Exception_swt());

        addViewBuilder(new Date_swt());
        addViewBuilder(new File_swt());
    }

    @Override
    protected Collection<ITypeMapper> getTypeMappers() {
        return swtTmaps;
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
