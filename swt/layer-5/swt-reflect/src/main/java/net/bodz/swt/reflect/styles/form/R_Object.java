package net.bodz.swt.reflect.styles.form;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.ui.RenderException;
import net.bodz.swt.reflect.GUIVar;
import net.bodz.swt.reflect.SWTRenderContext;
import net.bodz.swt.reflect.styles.base._R_Object;

public class R_Object
        extends _R_Object {

    protected final SWTFormStrategy strategy;

    public R_Object(SWTFormStrategy formStyle) {
        this.strategy = formStyle;
    }

    @Override
    protected Control renderObject(final SWTRenderContext rc, GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        assert var != null;
        // GUIVarMeta meta = var.getMeta();
        return null;
    }

}
