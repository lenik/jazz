package net.bodz.bas.c.action;

import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dialog.IUserDialogs;

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