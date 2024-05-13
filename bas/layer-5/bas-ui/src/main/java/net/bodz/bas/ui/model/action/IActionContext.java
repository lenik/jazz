package net.bodz.bas.ui.model.action;

import net.bodz.bas.rtx.IMutableAttributes;
import net.bodz.bas.ui.dialog.IUserDialogs;

public interface IActionContext
        extends
        // IQueryable,
        IMutableAttributes {

    IProgressMonitor getProgressMonitor();

    IUserDialogs getUserDialogs();

    ITransactionLog getTransactionLog();

}
