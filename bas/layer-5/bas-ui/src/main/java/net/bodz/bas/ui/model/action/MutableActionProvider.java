package net.bodz.bas.ui.model.action;

import java.util.ArrayList;
import java.util.List;


public abstract class MutableActionProvider
        implements IActionProvider {

    private List<IAction> actions;

    public MutableActionProvider() {
        actions = new ArrayList<IAction>();
    }

    @Override
    public Class<?> getTargetClass() {
        return void.class;
    }

    @Override
    public List<IAction> getActions(Object o) {
        return actions;
    }

    public void addAction(IAction action) {
        if (action == null)
            throw new NullPointerException("action");
        actions.add(action);
    }

    public void removeAction(IAction action) {
        actions.remove(action);
    }

}
