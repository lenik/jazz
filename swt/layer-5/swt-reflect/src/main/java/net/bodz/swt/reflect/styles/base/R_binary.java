package net.bodz.swt.reflect.styles.base;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.ui.RenderException;
import net.bodz.swt.reflect.GUIVar;
import net.bodz.swt.reflect.SWTRenderContext;
import net.bodz.swt.reflect.SWTRenderer;

public class R_binary
        extends SWTRenderer {

    @Override
    public Control render(final SWTRenderContext rc, GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        throw new NotImplementedException();
    }

}
