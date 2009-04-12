package net.bodz.swt.gui.styles.base;

import net.bodz.bas.gui.RenderException;
import net.bodz.swt.controls.helper.DynamicControl;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.GUIVarMeta;
import net.bodz.swt.gui.RenderContext;
import net.bodz.swt.gui.SWTRenderer;
import net.bodz.swt.nls.GUINLS;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public abstract class _R_Object extends SWTRenderer {

    public _R_Object(RenderContext rc) {
        super(rc);
    }

    @Override
    public Control render(GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        GUIVarMeta meta = var.getMeta();
        Control control = null;
        try {
            if (meta.isReadOnly()) {
                Object value = var.get();
                if (value == null) {
                    Label label = new Label(parent, style);
                    label.setText(GUINLS.getString("_R_Object.null")); //$NON-NLS-1$
                    return control = label;
                } else {
                    return control = renderObject(var, parent, style);
                }
            }
            DynamicControl dyna = new DynamicControl(parent, SWT.NONE);
            control = renderObject(var, dyna, style);
            return dyna;
        } finally {
            if (control != null)
                rc.addEffects(control, var);
        }
    }

    protected abstract Control renderObject(GUIVar<?> var, Composite parent,
            int style) throws RenderException, SWTException;

}
