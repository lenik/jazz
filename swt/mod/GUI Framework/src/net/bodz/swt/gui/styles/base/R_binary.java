package net.bodz.swt.gui.styles.base;

import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.ui.RenderException;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.SWTRenderContext;
import net.bodz.swt.gui.SWTRenderer;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class R_binary extends SWTRenderer {

    @Override
    public Control render(final SWTRenderContext rc, GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        throw new NotImplementedException();
    }

}
