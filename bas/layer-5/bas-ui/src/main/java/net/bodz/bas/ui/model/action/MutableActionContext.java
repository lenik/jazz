package net.bodz.bas.ui.model.action;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.ui.dialog.IUserDialogs;

public class MutableActionContext
        implements IActionContext {

    private Map<String, Object> attributes;
    private IProgressMonitor progressMonitor;
    private IUserDialogs userDialogs;
    private ITransactionLog transactionLog;

    public MutableActionContext() {
        this.attributes = new HashMap<>();
    }

    public Map<String, Object> getAttributeMap() {
        return attributes;
    }

    @Override
    public <T> T getAttribute(String name) {
        return (T) attributes.get(name);
    }

    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        T val = (T) attributes.get(name);
        if (val == null)
            val = defaultValue;
        return val;
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
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
