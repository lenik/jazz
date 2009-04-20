package net.bodz.swt.gui;

import java.beans.PropertyChangeListener;

import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.lang.ref.Var;
import net.bodz.bas.ui.RenderException;
import net.bodz.bas.ui.Renderer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class SWTRenderer implements Renderer {

    /**
     * @throw NullPointerException if var is null
     */
    @Override
    public Control render(Object context, Var<?> var) throws RenderException {
        if (var == null)
            throw new NullPointerException("render var"); //$NON-NLS-1$
        try {
            if (context != null && !(context instanceof SWTRenderContext))
                throw new OutOfDomainException("context", context, //$NON-NLS-1$
                        SWTRenderContext.class);
            if (!(var instanceof GUIVar<?>))
                throw new RenderException("not a GUIVar: " + var.getClass()); //$NON-NLS-1$
            // XXX - or create a new swt-context to include the object-context?
            SWTRenderContext rc = null; // (SWTRenderContext) context;
            GUIVar<?> gvar = (GUIVar<?>) var;
            return render(rc, gvar, null, SWT.NONE);
        } catch (SWTException e) {
            throw new RenderException(e);
        }
    }

    public abstract Control render(SWTRenderContext rc, GUIVar<?> var,
            Composite parent, int style) throws RenderException, SWTException;

    protected void bindProperty(final GUIVar<?> var, final Control control,
            final PropertyChangeListener listener) {
        var.addPropertyChangeListener(listener);
        control.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                var.removePropertyChangeListener(listener);
            }
        });
    }

}
