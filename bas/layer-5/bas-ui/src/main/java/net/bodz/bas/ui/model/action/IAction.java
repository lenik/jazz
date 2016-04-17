package net.bodz.bas.ui.model.action;

import net.bodz.bas.inject.LocalScope;

/**
 * Local scope by default.
 */
@LocalScope
public interface IAction {

    boolean canPlay();

    Object play(IActionContext context)
            throws Exception;

    boolean canRollback();

    void rollback(IActionContext context)
            throws RollbackException;

    IAction NULL = new NullAction();

}

class NullAction
        implements IAction {

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public Object play(IActionContext context)
            throws Exception {
        return null;
    }

    @Override
    public boolean canRollback() {
        return true;
    }

    @Override
    public void rollback(IActionContext context)
            throws RollbackException {
    }

}