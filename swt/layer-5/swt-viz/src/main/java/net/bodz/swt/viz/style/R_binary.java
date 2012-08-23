package net.bodz.swt.viz.style;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.SWTRenderer;
import net.bodz.swt.viz.core.SwtEntry;

public class R_binary
        extends SWTRenderer {

    @Override
    public Control render(final SWTRenderContext rc, SwtEntry<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        throw new NotImplementedException();
    }

}
