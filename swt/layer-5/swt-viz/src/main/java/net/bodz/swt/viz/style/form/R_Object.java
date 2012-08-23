package net.bodz.swt.viz.style.form;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.core.SwtEntry;
import net.bodz.swt.viz.style._R_Object;

public class R_Object
        extends _R_Object {

    protected final FormVisualization strategy;

    public R_Object(FormVisualization formStyle) {
        this.strategy = formStyle;
    }

    @Override
    protected Control renderObject(final SWTRenderContext rc, SwtEntry<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        assert var != null;
        // GUIVarMeta meta = var.getMeta();
        return null;
    }

}
