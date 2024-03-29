package net.bodz.swt.viz;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.IValueChangeSource;
import net.bodz.bas.repr.viz.AbstractViewBuilder;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.ui.dom1.IUiRef;

public abstract class AbstractSwtViewBuilder<T>
        extends AbstractViewBuilder<T>
        implements
            ISwtViewBuilder<T>,
            II18nCapable {

    public AbstractSwtViewBuilder(Class<?> valueClass) {
        super(valueClass);
    }

    @Override
    public final Object buildViewStart(IQueryable ctx, Object _parent, IUiRef<T> ref)
            throws ViewBuilderException {
        Composite parent = null;
        if (_parent != null) {
            if (!(_parent instanceof Control))
                throw new IllegalUsageException("parent isn't an SWT Composite: " + _parent.getClass());
            parent = (Composite) _parent;
        }

        ISwtUiRef<T> swtRef;
        if (ref instanceof ISwtUiRef<?>) {
            swtRef = (ISwtUiRef<T>) ref;
        } else {
            swtRef = new DefaultSwtUiRef<>(ref);
        }

        int styleInt = getAttribute("swt.styleInt", SWT.NONE);

        return buildSwtView(ctx, parent, swtRef, styleInt);
    }

    @Override
    public final Widget buildSwtView(IQueryable ctx, Composite parent, ISwtUiRef<T> ref, int styleInt)
            throws ViewBuilderException {
        return buildSwtView(ctx, parent, ref, styleInt, IOptions.NULL);
    }

    protected void bindProperty(final IRefEntry<?> entry, final Control control, final IValueChangeListener listener) {
        final IValueChangeSource valueChangeSource = entry.query(IValueChangeSource.class);
        valueChangeSource.addValueChangeListener(listener);
        control.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                valueChangeSource.removeValueChangeListener(listener);
            }
        });
    }

}
