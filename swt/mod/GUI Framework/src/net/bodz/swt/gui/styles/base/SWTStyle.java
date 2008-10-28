package net.bodz.swt.gui.styles.base;

import java.io.File;

import net.bodz.bas.gui.Interaction;
import net.bodz.bas.gui.RenderException;
import net.bodz.bas.gui.Renderer;
import net.bodz.bas.gui.Style;
import net.bodz.bas.lang.a.ChainUsage;
import net.bodz.bas.lang.a.OverrideOption;
import net.bodz.bas.lang.ref.Var;
import net.bodz.swt.gui.GUIHint;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.GUIVarMeta;
import net.bodz.swt.gui.RenderContext;
import net.bodz.swt.gui.SWTInteraction;
import net.bodz.swt.gui.SWTRenderer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class SWTStyle extends Style implements RenderContext {

    private static final long serialVersionUID = 4665944902525510516L;

    protected static class Config {
        public String defaultIcon     = "/icons/full/obj16/genericvariable_obj.gif";
        public int    defaultIconSize = 16;

        public static Config getDefault() {
            return instance;
        }

        private static Config instance = new Config();

    }

    public SWTStyle() {
        setup();
    }

    @OverrideOption(chain = ChainUsage.MUST)
    protected void setup() {
        put(String.class, new R_Text(this));
        put(Number.class, new R_Number(this));
        put(boolean.class, new R_Boolean(this));
        put(Boolean.class, new R_Boolean(this));
        put(Throwable.class, new R_Throwable(this));
        put(File.class, new R_File(this));
        put(byte[].class, new R_binary(this));
    }

    @Override
    public SWTRenderer put(Class<?> key, Renderer value) {
        if (!(value instanceof SWTRenderer))
            throw new IllegalArgumentException("not a SWTRenderer: " + value);
        return (SWTRenderer) super.put(key, value);
    }

    @Override
    public Control render(Var<?> var) throws RenderException {
        try {
            return render((GUIVar<?>) var, null, SWT.NONE);
        } catch (SWTException e) {
            throw new RenderException(e);
        }
    }

    public Control render(GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        SWTRenderer renderer = findRenderer(var);
        if (renderer == null)
            throw new RenderException("Don't know how to render "
                    + var.getMeta().getType());
        @SuppressWarnings("unchecked")
        GUIVar<Object> gvar = (GUIVar<Object>) var;
        return renderer.render(gvar, parent, style);
    }

    @Override
    protected SWTRenderer findRenderer(Var<?> var) {
        return (SWTRenderer) super.findRenderer(var);
    }

    @Override
    public Interaction interact(Control active) {
        return new SWTInteraction(active.getShell());
    }

    @Override
    public void addEffects(Control control, GUIVar<?> var)
            throws RenderException {
        GUIVarMeta meta = var.getMeta();
        GUIHint hint = meta.getHint();
        if (hint == null)
            return;
        Device device = control.getDisplay();
        if (hint.doc != null)
            control.setToolTipText(hint.doc);
        if (hint.visible != null)
            control.setVisible(hint.visible);
        if (hint.enabled != null)
            control.setEnabled(hint.enabled);
        if (hint.color != null)
            control.setForeground(new Color(device, hint.color));
        if (hint.backColor != null)
            control.setBackground(new Color(device, hint.backColor));
        Font font = hint.getFont(device);
        if (font != null)
            control.setFont(font);
        if (hint.preferredSize != null)
            control.setSize(hint.preferredSize);
    }

}
