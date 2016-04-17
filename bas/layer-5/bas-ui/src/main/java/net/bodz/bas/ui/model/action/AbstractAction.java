package net.bodz.bas.ui.model.action;

public abstract class AbstractAction
        implements IAction {

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public boolean canRollback() {
        return false;
    }

    @Override
    public void rollback(IActionContext context)
            throws RollbackException {
    }

}
