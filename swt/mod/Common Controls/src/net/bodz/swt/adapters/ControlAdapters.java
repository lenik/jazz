package net.bodz.swt.adapters;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Control;

public class ControlAdapters {

    // ...?
    public static void loseFocus(final Control control,
            final LoseFocusListener loseFocusListener) {
        control.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                boolean doit = loseFocusListener.loseFocus(e);
                if (!doit) {
                    control.setFocus();
                }
            }
        });
    }

    public static void commit(final Control control,
            final CommitListener commitListener,
            final CommitFailListener commitFailListener) {
        control.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent event) {
                try {
                    commitListener.commit(event);
                } catch (CommitException e) {
                    // focus problem..
                    if (commitFailListener != null)
                        commitFailListener.commitFail(event, e);
                    control.setFocus();
                }
            }
        });
    }

    public static void commit(Control control, CommitAdapter commitAdapter) {
        commit(control, commitAdapter, commitAdapter);
    }

}
