package net.bodz.swt.viz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.gui.viz.IRenderer;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;

public abstract class SwtRenderer
        implements IRenderer, II18nCapable {

    /**
     * @throws NullPointerException
     *             if var is null
     */
    @Override
    public Control render(Object context, IRefEntry<?> entry)
            throws RenderException {
        if (entry == null)
            throw new NullPointerException("render var");
        try {
            if (context != null && !(context instanceof SwtRenderContext))
                throw new OutOfDomainException("context", context, SwtRenderContext.class);

            SwtVizStyleClass style = null;
            if (entry instanceof IRefEntry_SWT<?>)
                style = ((IRefEntry_SWT<?>) entry).getStyle();

            // XXX - or create a new swt-context to include the object-context?
            SwtRenderContext rc = null; // (SWTRenderContext) context;

            return render(rc, entry, style, null, SWT.NONE);

        } catch (SWTException e) {
            throw new RenderException(e);
        }
    }

    public abstract Control render(SwtRenderContext rc, IRefEntry<?> entry, SwtVizStyleClass stylesheet,
            Composite parent, int swtStyle)
            throws RenderException, SWTException;

    protected void bindProperty(final IRefEntry<?> entry, final Control control, final IValueChangeListener listener) {
        entry.addValueChangeListener(listener);
        control.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                entry.removeValueChangeListener(listener);
            }
        });
    }

}