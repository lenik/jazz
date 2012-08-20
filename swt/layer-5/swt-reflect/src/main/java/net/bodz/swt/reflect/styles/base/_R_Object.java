package net.bodz.swt.reflect.styles.base;

import static net.bodz.swt.nls.GUINLS.GUINLS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import net.bodz.bas.ui.RenderException;
import net.bodz.swt.c.control.DynamicControl;
import net.bodz.swt.reflect.GUIVar;
import net.bodz.swt.reflect.GUIVarMeta;
import net.bodz.swt.reflect.SWTRenderContext;
import net.bodz.swt.reflect.SWTRenderer;

public abstract class _R_Object
        extends SWTRenderer {

    @Override
    public Control render(final SWTRenderContext rc, GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        GUIVarMeta meta = var.getMeta();
        Control control = null;
        try {
            if (meta.isReadOnly()) {
                Object value = var.get();
                if (value == null) {
                    Label label = new Label(parent, style);
                    label.setText(GUINLS.getString("_R_Object.null"));
                    return control = label;
                } else {
                    return control = renderObject(rc, var, parent, style);
                }
            } else {
                DynamicControl dyna = new DynamicControl(parent, SWT.NONE);
                control = renderObject(rc, var, dyna, style);
                return dyna;
            }
        } finally {
            if (control != null)
                rc.addEffects(control, var);
        }
    }

    protected abstract Control renderObject(final SWTRenderContext rc, GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException;

}
