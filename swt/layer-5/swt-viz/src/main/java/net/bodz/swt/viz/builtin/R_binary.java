package net.bodz.swt.viz.builtin;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.SwtRenderer;
import net.bodz.swt.viz.SwtVizStyleClass;

public class R_binary
        extends SwtRenderer {

    @Override
    public Control render(final SwtRenderContext rc, IRefEntry<?> entry, SwtVizStyleClass stylesheet, Composite parent,
            int style)
            throws RenderException, SWTException {
        throw new NotImplementedException();
    }

}
