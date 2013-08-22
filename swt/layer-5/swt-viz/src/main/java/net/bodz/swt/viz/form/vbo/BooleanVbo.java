package net.bodz.swt.viz.form.vbo;

import java.util.EventObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.mf.std.ValidationException;
import net.bodz.swt.c3.control.CommitAdapter;
import net.bodz.swt.c3.control.CommitException;
import net.bodz.swt.c3.control.ControlAdapters;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.AbstractSwtViewBuilder;

public class BooleanVbo
        extends AbstractSwtViewBuilder<Boolean> {

    @Override
    public Control buildView(final SwtRenderContext rc, final IRefEntry<Boolean> entry, ISwtControlStyleDeclaration style,
            Composite parent, int styleInt)
            throws ViewBuilderException, SWTException {

        boolean readOnly = style.getReadOnly() == Boolean.TRUE;

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
        if (entry.isValueChangeSource())
            bindProperty(entry, check, new IValueChangeListener() {
                @Override
                public boolean valueChange(ValueChangeEvent event) {
                    Boolean _val = (Boolean) entry.get();
                    check.setSelection(_val == null ? false : _val);
                    return true;
                }
            });
        if (readOnly) {
            ControlAdapters.autocommitForFocus(check, new CommitAdapter(rc.getUserDialogs(check)) {
                @Override
                public void commit(EventObject event)
                        throws CommitException {
                    boolean val = check.getSelection();
                    try {
                        entry.validate(val);
                        entry.set(val);
                    } catch (ValidationException e) {
                        throw new CommitException(e);
                    }
                }
            });
        }
        rc.addEffects(check, style);
        return check;
    }
}
