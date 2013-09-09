package net.bodz.swt.viz;

import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.swt.c.dialog.SwtDialogs;
import net.bodz.swt.gui.model.ICommand;

public class SwtRenderContext {

    public IUserDialogs getUserDialogs(Control active) {
        return new SwtDialogs(active.getShell());
    }

    public void addAction(ICommand action) {
    }

}
