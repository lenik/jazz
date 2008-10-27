package net.bodz.swt.adapters;

import java.util.EventObject;

import net.bodz.bas.gui.Interaction;

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

public abstract class CommitAdapter implements CommitListener,
        CommitFailListener {

    private Interaction interaction;

    public CommitAdapter(Interaction interaction) {
        this.interaction = interaction;
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
        interaction.alert("Commit Error", cause);
        Widget widget = (Widget) evt.widget;
        // System.out.println("  source=" + evt.getSource());
        // System.out.println("  widget=" + evt.widget);
        if (widget instanceof Text) {
            Text text = (Text) widget;
            text.setSelection(0, text.getText().length());
        }
    }

}
