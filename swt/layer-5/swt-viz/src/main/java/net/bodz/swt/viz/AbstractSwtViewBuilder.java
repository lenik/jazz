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

public abstract class AbstractSwtViewBuilder<T>
        extends AbstractViewBuilder<T>
        implements ISwtViewBuilder<T>, II18nCapable {

    public AbstractSwtViewBuilder(Class<?> valueClass, String... supportedFeatures) {
        super(valueClass, supportedFeatures);
    }

    @Override
    public final Object buildView(Object ctx, IRefEntry<T> entry, IOptions options)
            throws ViewBuilderException {
        Composite parent = null;
        if (ctx != null) {
            if (!(ctx instanceof Control))
                throw new IllegalUsageException("ctx isn't an SWT Composite: " + ctx.getClass());
            parent = (Composite) ctx;
        }

        // TODO wrap...
        ISwtGUIRefEntry<T> wrapper = (ISwtGUIRefEntry<T>) entry;

        int styleInt = options.getInt("swt.styleInt", SWT.NONE);

        return buildSwtView(parent, wrapper, styleInt, options);
    }

    @Override
    public final Widget buildSwtView(Composite parent, ISwtGUIRefEntry<T> entry, int styleInt)
            throws ViewBuilderException {
        return buildSwtView(parent, entry, styleInt, IOptions.NULL);
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
