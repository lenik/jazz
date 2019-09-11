package net.bodz.bas.ui.model.action;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.ui.dom1.UiObject;

public abstract class UiAction
        extends UiObject
        implements IAction {

    private List<Class<?>> locations;

    public UiAction() {
        locations = new ArrayList<Class<?>>();
        Location aLocation = getClass().getAnnotation(Location.class);
        if (aLocation != null)
            for (Class<?> c : aLocation.value())
                locations.add(c);
    }

    @Override
    public List<Class<?>> getLocations() {
        return locations;
    }

    @Override
    public Class<?> getTargetClass() {
        return null;
    }

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
