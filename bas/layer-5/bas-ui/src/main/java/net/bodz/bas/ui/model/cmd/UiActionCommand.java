package net.bodz.bas.ui.model.cmd;

import java.util.List;

import net.bodz.bas.ui.dom1.UiObject;
import net.bodz.bas.ui.model.action.IAction;
import net.bodz.bas.ui.model.action.IActionContext;
import net.bodz.bas.ui.model.action.RollbackException;

public abstract class UiActionCommand
        extends UiObject
        implements ICommand, IAction {

    private List<Class<?>> locations;

    @Override
    public Class<?> getTargetClass() {
        return void.class;
    }

    @Override
    public List<Class<?>> getLocations() {
        return locations;
    }

    @Override
    public boolean isApplicable() {
        return true;
    }

    @Override
    public IAction getAction() {
        return this;
    }

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
