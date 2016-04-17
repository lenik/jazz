package net.bodz.swt.viz;

import org.eclipse.swt.widgets.Control;

import net.bodz.bas.ui.dialog.IUserDialogs;
import net.bodz.bas.ui.model.cmd.ICommand;
import net.bodz.swt.c.dialog.SwtUserDialogs;

public class SwtRenderContext {

    public IUserDialogs getUserDialogs(Control active) {
        return new SwtUserDialogs(active.getShell());
    }

    public void addAction(ICommand action) {
    }

}
