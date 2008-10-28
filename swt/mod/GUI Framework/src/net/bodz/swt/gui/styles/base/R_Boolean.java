package net.bodz.swt.gui.styles.base;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;

import net.bodz.bas.gui.RenderException;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.swt.adapters.CommitAdapter;
import net.bodz.swt.adapters.CommitException;
import net.bodz.swt.adapters.ControlAdapters;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.GUIVarMeta;
import net.bodz.swt.gui.RenderContext;
import net.bodz.swt.gui.SWTRenderer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class R_Boolean extends SWTRenderer {

    public R_Boolean(RenderContext rc) {
        super(rc);
    }

    @Override
    public Control render(final GUIVar<?> var, final Composite parent, int style)
            throws RenderException, SWTException {
        GUIVarMeta meta = var.getMeta();
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
            ControlAdapters.commit(check,
                    new CommitAdapter(rc.interact(check)) {
                        @Override
                        public void commit(EventObject event)
                                throws CommitException {
                            boolean val = check.getSelection();
                            try {
                                var.check(val);
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
