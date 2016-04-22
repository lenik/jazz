package net.bodz.bas.ui.model.action;

public abstract class AbstractAction
        implements IAction {

    @Override
    public boolean canRun(Object obj) {
        return true;
    }

    @Override
    public boolean canRollback(Object obj) {
        return false;
    }

    @Override
    public void rollback(Object obj, IActionContext context)
            throws RollbackException {
    }

}
