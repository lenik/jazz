package net.bodz.swt.c.control;

import java.util.EventObject;

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

public abstract class CommitAdapter
        implements CommitListener, CommitFailListener {

    private UserInterface UI;

    public CommitAdapter(UserInterface userInterface) {
        this.UI = userInterface;
    }

    /**
     * Do nothing in default implementation.
     */
    @Override
    public void commitFail(EventObject event, CommitException exception) {
        Throwable cause = exception.getCause();
        if (cause == null)
            cause = exception;
        TypedEvent evt = (TypedEvent) event;
        UI.alert(ControlsNLS.getString("CommitAdapter.commitError"), cause);
        Widget widget = evt.widget;
        // System.out.println("  source=" + evt.getSource());
        // System.out.println("  widget=" + evt.widget);
        if (widget instanceof Text) {
            Text text = (Text) widget;
            text.setSelection(0, text.getText().length());
        }
    }

}
