package net.bodz.swt.reflect;

import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.ui.IRenderer;
import net.bodz.bas.ui.RenderException;
import net.bodz.bas.ui.Var;

public abstract class SWTRenderer
        implements IRenderer {

    /**
     * @throw NullPointerException if var is null
     */
    @Override
    public Control render(Object context, Var<?> var)
            throws RenderException {
        if (var == null)
            throw new NullPointerException("render var");
        try {
            if (context != null && !(context instanceof SWTRenderContext))
                throw new OutOfDomainException("context", context, SWTRenderContext.class);
            if (!(var instanceof GUIVar<?>))
                throw new RenderException("not a GUIVar: " + var.getClass());
            // XXX - or create a new swt-context to include the object-context?
            SWTRenderContext rc = null; // (SWTRenderContext) context;
            GUIVar<?> gvar = (GUIVar<?>) var;
            return render(rc, gvar, null, SWT.NONE);
        } catch (SWTException e) {
            throw new RenderException(e);
        }
    }

    public abstract Control render(SWTRenderContext rc, GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException;

    protected void bindProperty(final GUIVar<?> var, final Control control, final PropertyChangeListener listener) {
        var.addPropertyChangeListener(listener);
        control.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                var.removePropertyChangeListener(listener);
            }
        });
    }

}
