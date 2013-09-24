package net.bodz.swt.viz;

import org.eclipse.swt.widgets.Control;

import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.swt.c.dialog.SwtUserDialogs;
import net.bodz.swt.gui.model.ICommand;

public class SwtRenderContext {

    public IUserDialogs getUserDialogs(Control active) {
        return new SwtUserDialogs(active.getShell());
    }

    public void addAction(ICommand action) {
    }

}
