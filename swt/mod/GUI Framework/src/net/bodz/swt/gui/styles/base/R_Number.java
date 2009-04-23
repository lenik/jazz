package net.bodz.swt.gui.styles.base;

import net.bodz.bas.ui.RenderException;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.SWTRenderContext;
import net.bodz.swt.gui.SWTRenderer;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class R_Number extends SWTRenderer {

    @Override
    public Control render(SWTRenderContext rc, GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        // GUIVarMeta meta = var.getMeta();
        // if min/max then render in slider...
        return new R_Text().render(rc, var, parent, style);
    }

}
