package net.bodz.swt.gui;

import java.beans.PropertyChangeListener;

import net.bodz.bas.gui.RenderException;
import net.bodz.bas.gui.Renderer;
import net.bodz.bas.lang.ref.Var;

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
    public Control render(Var<?> var) throws RenderException {
        if (var == null)
            throw new NullPointerException("render var");
        try {
            if (!(var instanceof GUIVar))
                throw new RenderException("not a GUIVar: " + var.getClass());
            GUIVar<?> gvar = (GUIVar<?>) var;
            return render(gvar, null, SWT.NONE);
        } catch (SWTException e) {
            throw new RenderException(e);
        }
    }

    protected abstract Control render(GUIVar<?> var, Composite parent,
            int style) throws RenderException, SWTException;

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
