package net.bodz.swt.viz;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.repr.viz.AbstractViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.NoOptions;

public abstract class AbstractSwtViewBuilder<T>
        extends AbstractViewBuilder<T>
        implements ISwtViewBuilder<T>, II18nCapable {

    @Override
    public final Widget buildView(Control parent, ISwtGUIRefEntry<T> entry)
            throws ViewBuilderException {
        return buildView(parent, entry, NoOptions.getInstance());
    }

    public abstract Control buildView(SwtRenderContext rc, IRefEntry<T> entry, ISwtControlStyleDeclaration styleDecl,
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
