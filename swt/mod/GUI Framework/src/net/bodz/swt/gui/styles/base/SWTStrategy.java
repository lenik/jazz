package net.bodz.swt.gui.styles.base;

import java.io.File;

import net.bodz.bas.lang.a.ChainUsage;
import net.bodz.bas.lang.a.OverrideOption;
import net.bodz.bas.lang.ref.Var;
import net.bodz.bas.ui.RenderException;
import net.bodz.bas.ui.RenderStrategy;
import net.bodz.bas.ui.Renderer;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.GUIVars;
import net.bodz.swt.gui.SWTRenderContext;
import net.bodz.swt.gui.SWTRenderer;
import net.bodz.swt.nls.GUINLS;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class SWTStrategy extends RenderStrategy {

    private static final long serialVersionUID = 4665944902525510516L;

    protected static class Config {
        public String defaultIcon     = "/icons/full/obj16/genericvariable_obj.gif"; //$NON-NLS-1$
        public int    defaultIconSize = 16;

        public static Config getDefault() {
            return instance;
        }

        private static Config instance = new Config();

    }

    public SWTStrategy() {
        setup();
    }

    @OverrideOption(chain = ChainUsage.MUST)
    protected void setup() {
        put(String.class, new R_Text());
        put(Number.class, new R_Number());
        put(boolean.class, new R_Boolean());
        put(Boolean.class, new R_Boolean());
        put(Throwable.class, new R_Throwable());
        put(File.class, new R_File());
        put(byte[].class, new R_binary());
    }

    @Override
    public SWTRenderer put(Class<?> key, Renderer value) {
        if (!(value instanceof SWTRenderer))
            throw new IllegalArgumentException(
                    GUINLS.getString("SWTStrategy.notSWTRenderer") + value); //$NON-NLS-1$
        return (SWTRenderer) super.put(key, value);
    }

    @Override
    public Control render(Object context, Var<?> var) throws RenderException {
        try {
            SWTRenderContext rc = null; // new SWTRenderContext();
            return render(rc, (GUIVar<?>) var);
        } catch (SWTException e) {
            throw new RenderException(e);
        }
    }

    public Control render(SWTRenderContext rc, GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc"); //$NON-NLS-1$
        SWTRenderer renderer = findRenderer(var);
        if (renderer == null)
            throw new RenderException(GUINLS.getString("SWTStrategy.nullRenderer") //$NON-NLS-1$
                    + var.getMeta().getType());
        @SuppressWarnings("unchecked")
        GUIVar<Object> gvar = (GUIVar<Object>) var;
        return renderer.render(rc, gvar, parent, style);
    }

    public Control render(SWTRenderContext rc, Object constantValue, Composite parent, int style)
            throws RenderException, SWTException {
        if (rc == null)
            throw new NullPointerException("rc"); //$NON-NLS-1$
        GUIVar<Object> var = GUIVars.wrap(constantValue);
        return render(rc, var, parent, style);
    }

    @Override
    protected SWTRenderer findRenderer(Var<?> var) {
        return (SWTRenderer) super.findRenderer(var);
    }

}
