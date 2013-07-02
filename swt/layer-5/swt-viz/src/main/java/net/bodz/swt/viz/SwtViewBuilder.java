package net.bodz.swt.viz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.repr.viz.IViewBuilder;
import net.bodz.bas.repr.viz.ViewBuildOption;
import net.bodz.bas.repr.viz.ViewBuilderException;

public abstract class SwtViewBuilder<T>
        implements IViewBuilder<T>, II18nCapable {

    /**
     * @throws NullPointerException
     *             if var is null
     */
    @Override
    public Object buildView(Object ctx, IRefEntry<T> entry, ViewBuildOption... options)
            throws ViewBuilderException {
        if (entry == null)
            throw new NullPointerException("render var");
        try {
            if (ctx != null && !(ctx instanceof SwtRenderContext))
                throw new OutOfDomainException("context", ctx, SwtRenderContext.class);

            ISwtControlStyleClass style = null;
            if (entry instanceof ISwtGUIRefEntry<?>)
                style = ((ISwtGUIRefEntry<?>) entry).getStyle();

            // XXX - or create a new swt-context to include the object-context?
            SwtRenderContext rc = null; // (SWTRenderContext) context;

            return buildView(rc, entry, style, null, SWT.NONE);

        } catch (SWTException e) {
            throw new ViewBuilderException(e);
        }
    }

    public abstract Control buildView(SwtRenderContext rc, IRefEntry<T> entry, ISwtControlStyleClass style,
            Composite parent, int styleInt)
            throws ViewBuilderException, SWTException;

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
