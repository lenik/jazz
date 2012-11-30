package net.bodz.swt.viz;

import static net.bodz.swt.nls.GUINLS.GUINLS;

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

public abstract class SWTVisualization
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

    public SWTVisualization() {
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
    public SWTRenderer put(Class<?> key, IRenderer value) {
        if (!(value instanceof SWTRenderer))
            throw new IllegalArgumentException(tr._("not a SWTRenderer: ") + value);
        return (SWTRenderer) super.put(key, value);
    }

    @Override
    public Control render(Object context, IRefEntry<?> entry)
            throws RenderException {
        try {
            SWTRenderContext rc = null; // new SWTRenderContext();
            return render(rc, entry);
        } catch (SWTException e) {
            throw new RenderException(e);
        }
    }

    public Control render(SWTRenderContext rc, IRefEntry<?> entry, SwtStyleClass stylesheet, Composite parent, int style)
            throws RenderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc");
        SWTRenderer renderer = findRenderer(entry);
        if (renderer == null) {
            String errmesg = tr._("Don\'t know how to render ") + entry.getName();
            entry = GUIVars.wrap(errmesg);
            renderer = findRenderer(entry);
            throw new RenderException(errmesg); // XXX -
        }
        return renderer.render(rc, entry, stylesheet, parent, style);
    }

    public Control render(SWTRenderContext rc, Object constantValue, Composite parent, int style)
            throws RenderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc");
        IRefEntry_SWT<Object> var = GUIVars.wrap(constantValue);
        return render(rc, var, parent, style);
    }

    @Override
    protected SWTRenderer findRenderer(IRefEntry<?> entry) {
        return (SWTRenderer) super.findRenderer(entry);
    }

}
