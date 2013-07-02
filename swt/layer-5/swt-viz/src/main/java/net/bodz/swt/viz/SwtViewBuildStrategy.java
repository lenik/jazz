package net.bodz.swt.viz;

import java.io.File;
import java.util.Date;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.meta.source.ChainUsage;
import net.bodz.bas.meta.source.OverrideOption;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.repr.viz.AbstractViewBuildStrategy;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.repr.viz.ViewBuildOption;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.swt.viz.form.vbo.BooleanVbo;
import net.bodz.swt.viz.form.vbo.DateVbo;
import net.bodz.swt.viz.form.vbo.ExceptionVbo;
import net.bodz.swt.viz.form.vbo.FileVbo;
import net.bodz.swt.viz.form.vbo.NumberVbo;
import net.bodz.swt.viz.form.vbo.StringVbo;

public abstract class SwtViewBuildStrategy
        extends AbstractViewBuildStrategy {

    private static final long serialVersionUID = 4665944902525510516L;

    public SwtViewBuildStrategy() {
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
    public Control buildView(Object ctx, IRefEntry<?> entry, ViewBuildOption... options)
            throws ViewBuilderException {
        try {
            SwtRenderContext rc = null; // new SWTRenderContext();
            return buildView(rc, entry);
        } catch (SWTException e) {
            throw new ViewBuilderException(e);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Control render(SwtRenderContext rc, IRefEntry<?> entry, ISwtControlStyleClass style, Composite parent,
            int styleInt)
            throws ViewBuilderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc");
        SwtViewBuilder builder = findViewBuilder(entry);
        if (builder == null) {
            String errMesg = tr._("Don\'t know how to render ") + entry.getName();
            entry = GUIVars.wrap(errMesg);
            builder = findViewBuilder(entry);
            throw new ViewBuilderException(errMesg); // XXX -
        }
        return builder.buildView(rc, entry, style, parent, styleInt);
    }

    public Control render(SwtRenderContext rc, Object constantValue, Composite parent, int styleInt)
            throws ViewBuilderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc");
        ISwtGUIRefEntry<Object> var = GUIVars.wrap(constantValue);
        return render(rc, var, parent, styleInt);
    }

    @Override
    protected <T> SwtViewBuilder<T> findViewBuilder(IRefEntry<? extends T> entry) {
        return (SwtViewBuilder<T>) super.findViewBuilder(entry);
    }

}
