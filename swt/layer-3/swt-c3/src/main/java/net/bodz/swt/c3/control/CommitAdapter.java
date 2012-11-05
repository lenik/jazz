package net.bodz.swt.c3.control;

import java.util.EventObject;

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.i18n.nls.II18nCapable;

public abstract class CommitAdapter
        implements CommitListener, CommitFailListener, II18nCapable {

    private IUserDialogs dialogs;

    public CommitAdapter(IUserDialogs dialogs) {
        if (dialogs == null)
            throw new NullPointerException("dialogs");
        this.dialogs = dialogs;
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
        dialogs.alert(tr._("Commit Error"), cause);
        Widget widget = evt.widget;
        // System.out.println("  source=" + evt.getSource());
        // System.out.println("  widget=" + evt.widget);
        if (widget instanceof Text) {
            Text text = (Text) widget;
            text.setSelection(0, text.getText().length());
        }
    }

}
