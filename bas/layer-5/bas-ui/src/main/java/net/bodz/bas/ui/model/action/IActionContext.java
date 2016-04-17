package net.bodz.bas.ui.model.action;

import net.bodz.bas.rtx.IAttributed;
import net.bodz.bas.ui.dialog.IUserDialogs;

public interface IActionContext
        extends
        // IQueryable,
        IAttributed {

    IProgressMonitor getProgressMonitor();

    IUserDialogs getUserDialogs();

    ITransactionLog getTransactionLog();

}
