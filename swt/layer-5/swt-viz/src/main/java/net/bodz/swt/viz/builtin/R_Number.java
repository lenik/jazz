package net.bodz.swt.viz.builtin;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.viz.RenderException;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.SWTRenderer;
import net.bodz.swt.viz.SwtStyleData;

public class R_Number
        extends SWTRenderer {

    @Override
    public Control render(SWTRenderContext rc, IRefEntry<?> entry, SwtStyleData stylesheet, Composite parent, int style)
            throws RenderException, SWTException {
        // GUIVarMeta meta = var.getMeta();
        // if min/max then render in slider...
        return new R_Text().render(rc, entry, stylesheet, parent, style);
    }

}
