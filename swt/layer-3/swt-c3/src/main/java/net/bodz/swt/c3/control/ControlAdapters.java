package net.bodz.swt.c3.control;

import java.awt.Desktop;
import java.net.URI;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import net.bodz.swt.c3.ia.SwtDialog;

public class ControlAdapters
        extends net.bodz.swt.c.control.ControlAdapters {

    public static void autocommitForFocus(final Control control, final CommitListener commitListener,
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

    public static void autocommitForFocus(Control control, CommitAdapter commitAdapter) {
        autocommitForFocus(control, commitAdapter, commitAdapter);
    }

    static class OpenBrowserAdapter
            extends SelectionAdapter {
        final Object address;

        public OpenBrowserAdapter() {
            this.address = null;
        }

        public OpenBrowserAdapter(Object address) {
            this.address = address;
        }

        @Override
        public void widgetSelected(SelectionEvent e) {
            try {
                String s = address != null ? address.toString() : e.text;
                URI uri = new URI(s);
                Desktop.getDesktop().browse(uri);
            } catch (Exception ex) {
                Shell shell = ((Control) e.widget).getShell();
                new SwtDialog(shell).alert(ex.getMessage(), ex);
            }
        }
    }

    public static void onclickBrowse(Link link) {
        link.addSelectionListener(new OpenBrowserAdapter());
    }

    public static void onclickBrowse(Button button, Object address) {
        button.addSelectionListener(new OpenBrowserAdapter(address));
    }

    public static void onclickBrowse(MenuItem menuItem, Object address) {
        menuItem.addSelectionListener(new OpenBrowserAdapter(address));
    }

}
