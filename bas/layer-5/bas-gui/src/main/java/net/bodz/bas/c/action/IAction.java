package net.bodz.bas.c.action;

import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.rtx.IOptions;

public interface IAction {

    /**
     * @see IProgressMonitor
     * @see IUserDialogs
     */
    void run(IOptions options);

    IAction NULL = new NullAction();

}

class NullAction
        implements IAction {

    @Override
    public void run(IOptions options) {
    }

}