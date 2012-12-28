package net.bodz.swt.viz;

import java.io.File;
import java.util.Date;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.viz.AbstractViewBuildStrategy;
import net.bodz.bas.gui.viz.IViewBuilder;
import net.bodz.bas.gui.viz.ViewBuilderException;
import net.bodz.bas.meta.source.ChainUsage;
import net.bodz.bas.meta.source.OverrideOption;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.swt.viz.form.*;
import net.bodz.swt.viz.form.vbo.BooleanVbo;
import net.bodz.swt.viz.form.vbo.DateVbo;
import net.bodz.swt.viz.form.vbo.ExceptionVbo;
import net.bodz.swt.viz.form.vbo.FileVbo;
import net.bodz.swt.viz.form.vbo.NumberVbo;
import net.bodz.swt.viz.form.vbo.StringVbo;

public abstract class SwtViewBuildStrategy
        extends AbstractViewBuildStrategy {

    private static final long serialVersionUID = 4665944902525510516L;

    protected static class Config {
        public String defaultIcon = "/icons/full/obj16/genericvariable_obj.gif";
        public int defaultIconSize = 16;

        public static Config getDefault() {
            return instance;
        }

        private static Config instance = new Config();

    }

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
    public SwtViewBuilder put(Class<?> key, IViewBuilder value) {
        if (!(value instanceof SwtViewBuilder))
            throw new IllegalArgumentException(tr._("not a SWTRenderer: ") + value);
        return (SwtViewBuilder) super.put(key, value);
    }

    @Override
    public Control buildView(Object context, IRefEntry<?> entry)
            throws ViewBuilderException {
        try {
            SwtRenderContext rc = null; // new SWTRenderContext();
            return buildView(rc, entry);
        } catch (SWTException e) {
            throw new ViewBuilderException(e);
        }
    }

    public Control render(SwtRenderContext rc, IRefEntry<?> entry, MappedSwtVizStyleClass stylesheet, Composite parent, int style)
            throws ViewBuilderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc");
        SwtViewBuilder renderer = findViewBuilder(entry);
        if (renderer == null) {
            String errMesg = tr._("Don\'t know how to render ") + entry.getName();
            entry = GUIVars.wrap(errMesg);
            renderer = findViewBuilder(entry);
            throw new ViewBuilderException(errMesg); // XXX -
        }
        return renderer.buildView(rc, entry, stylesheet, parent, style);
    }

    public Control render(SwtRenderContext rc, Object constantValue, Composite parent, int style)
            throws ViewBuilderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc");
        ISwtGUIRefEntry<Object> var = GUIVars.wrap(constantValue);
        return render(rc, var, parent, style);
    }

    @Override
    protected SwtViewBuilder findViewBuilder(IRefEntry<?> entry) {
        return (SwtViewBuilder) super.findViewBuilder(entry);
    }

}
