package net.bodz.bas.http.ui.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.bas.ui.dom1.UiObject;
import net.bodz.bas.ui.model.action.IActionContext;
import net.bodz.bas.ui.model.action.RollbackException;
import net.bodz.bas.ui.model.cmd.ICommand;

public abstract class UiServletCommand
        extends UiObject
        implements ICommand, IServletAction {

    private final List<Class<?>> locations;

    public UiServletCommand() {
        locations = new ArrayList<>();
    }

    @Override
    public Class<?> getTargetClass() {
        return void.class;
    }

    @Override
    public List<Class<?>> getLocations() {
        return locations;
    }

    protected void addLocation(Class<?> location) {
        if (location == null)
            throw new NullPointerException("location");
        locations.add(location);
    }

    @Override
    public boolean isApplicable() {
        return true;
    }

    @Override
    public IServletAction getAction() {
        return this;
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public final Object play(IActionContext context)
            throws Exception {
        IServletActionContext servletActionContext = (IServletActionContext) context;
        // IServletActionResult sar = new MyServletActionResult();
        return play(servletActionContext);
    }

    public Object play(IServletActionContext context) {
        return null;
    }

    @Override
    public boolean canRollback() {
        return false;
    }

    @Override
    public void rollback(IActionContext context)
            throws RollbackException {
    }

    @Override
    public boolean isAsync() {
        // getClass().isAnnotationPresent(Async)
        return getXjdoc().getTag("cmd.async") != null;
    }

    /**
     * @cmd.onclick
     * @cmd.async
     */
    @Override
    public Map<String, String> getScriptMap() {

        return null;
    }

    @Override
    public String getScript(String fn) {
        return null;
    }

}
