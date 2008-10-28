package net.bodz.swt.gui.styles.base;

import net.bodz.bas.gui.RenderException;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.RenderContext;
import net.bodz.swt.gui.SWTRenderer;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class R_Number extends SWTRenderer {

    private final R_Text rText;

    public R_Number(RenderContext rc) {
        super(rc);
        rText = new R_Text(rc);
    }

    @Override
    public Control render(GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        // GUIVarMeta meta = var.getMeta();
        // if min/max then render in slider...
        return rText.render(var, parent, style);
    }

}
