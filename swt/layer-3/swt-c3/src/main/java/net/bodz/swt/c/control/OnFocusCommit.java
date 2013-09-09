package net.bodz.swt.c.control;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Control;

public class OnFocusCommit
        extends FocusAdapter {

    CommitListener commitListener;
    CommitFailListener commitFailListener;

    public OnFocusCommit(CommitListener commitListener, CommitFailListener commitFailListener) {
        if (commitListener == null)
            throw new NullPointerException("commitListener");
        if (commitFailListener == null)
            throw new NullPointerException("commitFailListener");
        this.commitListener = commitListener;
        this.commitFailListener = commitFailListener;
    }

    @Override
    public void focusLost(FocusEvent event) {
        Control control = (Control) event.widget;
        try {
            commitListener.commit(event);
        } catch (CommitException e) {
            // focus problem..
            if (commitFailListener != null)
                commitFailListener.commitFail(event, e);
            control.setFocus();
        }
    }

    public static void apply(Control control, final CommitListener commitListener,
            final CommitFailListener commitFailListener) {
        control.addFocusListener(new OnFocusCommit(commitListener, commitFailListener));
    }

    public static void apply(Control control, CommitAdapter commitAdapter) {
        apply(control, commitAdapter, commitAdapter);
    }

}
