package net.bodz.swt.viz;

import org.eclipse.swt.widgets.Control;

import net.bodz.bas.ui.dialog.IUserDialogs;
import net.bodz.swt.c.dialog.SwtUserDialogs;
import net.bodz.swt.ui.model.ICommand;

public class SwtRenderContext {

    public IUserDialogs getUserDialogs(Control active) {
        return new SwtUserDialogs(active.getShell());
    }

    public void addAction(ICommand action) {
    }

}
