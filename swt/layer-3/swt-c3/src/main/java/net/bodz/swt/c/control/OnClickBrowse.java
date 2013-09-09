package net.bodz.swt.c.control;

import java.awt.Desktop;
import java.net.URI;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import net.bodz.swt.c.dialog.SwtDialogs;

public class OnClickBrowse
        extends SelectionAdapter {
    final Object address;

    public OnClickBrowse() {
        this.address = null;
    }

    public OnClickBrowse(Object address) {
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
            new SwtDialogs(shell).alert(ex.getMessage(), ex);
        }
    }

    public static void apply(Link link) {
        link.addSelectionListener(new OnClickBrowse());
    }

    public static void apply(Button button, Object address) {
        button.addSelectionListener(new OnClickBrowse(address));
    }

    public static void apply(MenuItem menuItem, Object address) {
        menuItem.addSelectionListener(new OnClickBrowse(address));
    }

}
