package net.bodz.swt.viz;

import java.io.File;
import java.util.Date;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.viz.AbstractVisualization;
import net.bodz.bas.gui.viz.IRenderer;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.bas.meta.source.ChainUsage;
import net.bodz.bas.meta.source.OverrideOption;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.swt.viz.builtin.*;

public abstract class SwtVisualization
        extends AbstractVisualization {

    private static final long serialVersionUID = 4665944902525510516L;

    protected static class Config {
        public String defaultIcon = "/icons/full/obj16/genericvariable_obj.gif";
        public int defaultIconSize = 16;

        public static Config getDefault() {
            return instance;
        }

        private static Config instance = new Config();

    }

    public SwtVisualization() {
        setup();
    }

    @OverrideOption(chain = ChainUsage.MUST)
    protected void setup() {
        put(byte[].class, new R_binary());
        put(boolean.class, new R_Boolean());
        put(Boolean.class, new R_Boolean());
        put(Number.class, new R_Number());
        put(String.class, new R_Text());
        put(Throwable.class, new R_Throwable());

        put(Date.class, new R_Date());
        put(File.class, new R_File());
    }

    @Override
    public SwtRenderer put(Class<?> key, IRenderer value) {
        if (!(value instanceof SwtRenderer))
            throw new IllegalArgumentException(tr._("not a SWTRenderer: ") + value);
        return (SwtRenderer) super.put(key, value);
    }

    @Override
    public Control render(Object context, IRefEntry<?> entry)
            throws RenderException {
        try {
            SwtRenderContext rc = null; // new SWTRenderContext();
            return render(rc, entry);
        } catch (SWTException e) {
            throw new RenderException(e);
        }
    }

    public Control render(SwtRenderContext rc, IRefEntry<?> entry, SwtVizStyleClass stylesheet, Composite parent, int style)
            throws RenderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc");
        SwtRenderer renderer = findRenderer(entry);
        if (renderer == null) {
            String errMesg = tr._("Don\'t know how to render ") + entry.getName();
            entry = GUIVars.wrap(errMesg);
            renderer = findRenderer(entry);
            throw new RenderException(errMesg); // XXX -
        }
        return renderer.render(rc, entry, stylesheet, parent, style);
    }

    public Control render(SwtRenderContext rc, Object constantValue, Composite parent, int style)
            throws RenderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc");
        IRefEntry_SWT<Object> var = GUIVars.wrap(constantValue);
        return render(rc, var, parent, style);
    }

    @Override
    protected SwtRenderer findRenderer(IRefEntry<?> entry) {
        return (SwtRenderer) super.findRenderer(entry);
    }

}
