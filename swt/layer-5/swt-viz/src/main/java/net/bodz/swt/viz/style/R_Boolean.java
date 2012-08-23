package net.bodz.swt.viz.style;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.err.CheckException;
import net.bodz.bas.gui.viz.RenderException;
import net.bodz.swt.c.control.CommitAdapter;
import net.bodz.swt.c.control.CommitException;
import net.bodz.swt.c.control.ControlAdapters;
import net.bodz.swt.viz.SWTRenderContext;
import net.bodz.swt.viz.SWTRenderer;
import net.bodz.swt.viz.core.SwtEntry;
import net.bodz.swt.viz.core.SwtEntryMetadata;

public class R_Boolean
        extends SWTRenderer {

    @Override
    public Control render(final SWTRenderContext rc, final SwtEntry<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        SwtEntryMetadata meta = var.getMetadata();
        Boolean _val = (Boolean) var.get();
        boolean val = _val == null ? false : _val;
        final Button check = new Button(parent, style | SWT.CHECK);
        check.setSelection(val);
        if (meta.isReadOnly())
            check.setEnabled(false);
        else {
            check.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    var.set(check.getSelection());
                }
            });
        }
        if (meta.hasPropertyChangeSupport())
            bindProperty(var, check, new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    Boolean _val = (Boolean) var.get();
                    check.setSelection(_val == null ? false : _val);
                }
            });
        if (!meta.isReadOnly()) {
            ControlAdapters.commit(check, new CommitAdapter(rc.interact(check)) {
                @Override
                public void commit(EventObject event)
                        throws CommitException {
                    boolean val = check.getSelection();
                    try {
                        var.validate(val);
                        var.set(val);
                    } catch (CheckException e) {
                        throw new CommitException(e);
                    }
                }
            });
        }
        rc.addEffects(check, var);
        return check;
    }
}
