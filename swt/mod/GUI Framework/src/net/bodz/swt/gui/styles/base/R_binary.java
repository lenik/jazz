package net.bodz.swt.gui.styles.base;

import net.bodz.bas.gui.RenderException;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.RenderContext;
import net.bodz.swt.gui.SWTRenderer;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class R_binary extends SWTRenderer {

    public R_binary(RenderContext rc) {
        super(rc);
    }

    @Override
    public Control render(GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        return null;
    }

}
