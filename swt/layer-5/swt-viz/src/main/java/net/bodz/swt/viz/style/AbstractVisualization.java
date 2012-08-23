package net.bodz.swt.viz.style;

import static net.bodz.swt.nls.GUINLS.GUINLS;

import java.io.File;
import java.util.Date;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.Var;
import net.bodz.bas.gui.viz.IRenderer;
import net.bodz.bas.gui.viz.IVisualization;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.bas.meta.codehint.ChainUsage;
import net.bodz.bas.meta.codehint.OverrideOption;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.SWTRenderer;
import net.bodz.swt.viz.core.GUIVars;
import net.bodz.swt.viz.core.SwtEntry;

public abstract class AbstractVisualization
        extends IVisualization {

    private static final long serialVersionUID = 4665944902525510516L;

    protected static class Config {
        public String defaultIcon = "/icons/full/obj16/genericvariable_obj.gif";
        public int defaultIconSize = 16;

        public static Config getDefault() {
            return instance;
        }

        private static Config instance = new Config();

    }

    public AbstractVisualization() {
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
            throw new IllegalArgumentException(GUINLS.getString("SWTStrategy.notSWTRenderer") + value);
        return (SWTRenderer) super.put(key, value);
    }

    @Override
    public Control render(Object context, Var<?> var)
            throws RenderException {
        try {
            SWTRenderContext rc = null; // new SWTRenderContext();
            return render(rc, var);
        } catch (SWTException e) {
            throw new RenderException(e);
        }
    }

    public Control render(SWTRenderContext rc, SwtEntry<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc");
        SWTRenderer renderer = findRenderer(var);
        if (renderer == null) {
            String errmesg = GUINLS.getString("SWTStrategy.nullRenderer") + var.getMetadata().getName();
            var = GUIVars.wrap(errmesg);
            renderer = findRenderer(var);
            throw new RenderException(errmesg); // XXX -
        }
        @SuppressWarnings("unchecked")
        SwtEntry<Object> gvar = (SwtEntry<Object>) var;
        return renderer.render(rc, gvar, parent, style);
    }

    public Control render(SWTRenderContext rc, Object constantValue, Composite parent, int style)
            throws RenderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc");
        SwtEntry<Object> var = GUIVars.wrap(constantValue);
        return render(rc, var, parent, style);
    }

    @Override
    protected SWTRenderer findRenderer(Var<?> var) {
        return (SWTRenderer) super.findRenderer(var);
    }

}
