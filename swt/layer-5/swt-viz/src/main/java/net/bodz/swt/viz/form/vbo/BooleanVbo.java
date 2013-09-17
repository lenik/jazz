package net.bodz.swt.viz.form.vbo;

import java.util.EventObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.IValueChangeSource;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.ValidationException;
import net.bodz.swt.c.control.CommitAdapter;
import net.bodz.swt.c.control.CommitException;
import net.bodz.swt.c.control.OnFocusCommit;
import net.bodz.swt.viz.AbstractSwtViewBuilder;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.ISwtGUIRefEntry;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.util.SwtControlStyler;

public class BooleanVbo
        extends AbstractSwtViewBuilder<Boolean> {

    @Override
    public Widget buildView(Composite parent, final ISwtGUIRefEntry<Boolean> entry, int styleInt, IOptions options)
            throws ViewBuilderException {
        final ISwtControlStyleDeclaration styleDecl = entry.getStyle();
        final SwtRenderContext rc = options.get(SwtRenderContext.class);

        boolean readOnly = styleDecl.getReadOnly() == Boolean.TRUE;

        Boolean _val = (Boolean) entry.get();
        boolean val = _val == null ? false : _val;
        final Button check = new Button(parent, styleInt | SWT.CHECK);
        check.setSelection(val);
        if (readOnly)
            check.setEnabled(false);
        else {
            check.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    entry.set(check.getSelection());
                }
            });
        }

        if (entry.query(IValueChangeSource.class) != null)
            bindProperty(entry, check, new IValueChangeListener() {
                @Override
                public boolean valueChange(ValueChangeEvent event) {
                    Boolean _val = (Boolean) entry.get();
                    check.setSelection(_val == null ? false : _val);
                    return true;
                }
            });

        if (!readOnly) {
            OnFocusCommit.apply(check, new CommitAdapter(rc.getUserDialogs(check)) {
                @Override
                public void commit(EventObject event)
                        throws CommitException {
                    boolean val = check.getSelection();
                    try {
                        IRefEntry.fn.validateAndSet(entry, val);
                    } catch (ValidationException e) {
                        throw new CommitException(e);
                    }
                }
            });
        }

        SwtControlStyler.apply(check, styleDecl);
        return check;
    }

}
