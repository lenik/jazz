package net.bodz.swt.viz.builtin;

import java.util.EventObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.viz.RenderException;
import net.bodz.bas.potato.model.IType;
import net.bodz.bas.potato.ref.IRefDescriptor;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IValueChangeListener;
import net.bodz.bas.potato.ref.ValueChangeEvent;
import net.bodz.bas.traits.ValidationException;
import net.bodz.swt.c3.control.CommitAdapter;
import net.bodz.swt.c3.control.CommitException;
import net.bodz.swt.c3.control.ControlAdapters;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.SWTRenderer;
import net.bodz.swt.viz.SwtStyleData;

public class R_Boolean
        extends SWTRenderer {

    @Override
    public Control render(final SWTRenderContext rc, final IRefEntry<?> entry, SwtStyleData stylesheet,
            Composite parent, int style)
            throws RenderException, SWTException {

        final IRefDescriptor descriptor = entry.getDescriptor();

        Boolean _val = (Boolean) entry.get();
        boolean val = _val == null ? false : _val;
        final Button check = new Button(parent, style | SWT.CHECK);
        check.setSelection(val);
        if (!descriptor.isWritable())
            check.setEnabled(false);
        else {
            check.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    entry.set(check.getSelection());
                }
            });
        }
        if (descriptor.isValueChangeSource())
            bindProperty(entry, check, new IValueChangeListener() {
                @Override
                public boolean valueChange(ValueChangeEvent event) {
                    Boolean _val = (Boolean) entry.get();
                    check.setSelection(_val == null ? false : _val);
                    return true;
                }
            });
        if (!descriptor.isWritable()) {
            ControlAdapters.autocommitForFocus(check, new CommitAdapter(rc.getUserDialogs(check)) {
                @Override
                public void commit(EventObject event)
                        throws CommitException {
                    IType type = descriptor.getPotatoType();
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
        rc.addEffects(check, stylesheet);
        return check;
    }
}
