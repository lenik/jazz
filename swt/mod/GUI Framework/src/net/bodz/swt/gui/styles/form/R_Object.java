package net.bodz.swt.gui.styles.form;

import net.bodz.bas.gui.RenderException;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.styles.base._R_Object;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class R_Object extends _R_Object {

    protected final SWTFormStyle formStyle;

    public R_Object(SWTFormStyle formStyle) {
        super(formStyle);
        this.formStyle = formStyle;
    }

    @Override
    protected Control renderObject(GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        assert var != null;
        // GUIVarMeta meta = var.getMeta();
        return null;
    }

}
