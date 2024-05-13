package net.bodz.bas.ui.model.action;

import java.util.Map;

import net.bodz.bas.typer.std.MutableTypedAttributes;
import net.bodz.bas.ui.dialog.IUserDialogs;

public class MutableActionContext
        extends MutableTypedAttributes
        implements
            IActionContext {

    private IProgressMonitor progressMonitor;
    private IUserDialogs userDialogs;
    private ITransactionLog transactionLog;

    public Map<String, Object> getAttributeMap() {
        return attributeMap;
    }

    @Override
    public IProgressMonitor getProgressMonitor() {
        return progressMonitor;
    }

    public void setProgressMonitor(IProgressMonitor progressMonitor) {
        this.progressMonitor = progressMonitor;
    }

    @Override
    public IUserDialogs getUserDialogs() {
        return userDialogs;
    }

    public void setUserDialogs(IUserDialogs userDialogs) {
        this.userDialogs = userDialogs;
    }

    @Override
    public ITransactionLog getTransactionLog() {
        return transactionLog;
    }

    public void setTransactionLog(ITransactionLog transactionLog) {
        this.transactionLog = transactionLog;
    }

}
