package net.bodz.bas.http.ui.cmd;

import java.util.Map;

import net.bodz.bas.ui.model.action.IAction;

/**
 * @see net.bodz.bas.ui.model.action.IAction
 * @see net.bodz.bas.ui.model.cmd.ICommand
 */
public interface IServletAction
        extends IAction {

    /**
     * For synchronous action, the caller will be blocked until the action is returned.
     * 
     * @return <code>true</code> for asynchronous servlet action.
     */
    boolean isAsync();

    Map<String, String> getScriptMap();

    /**
     * @param fn
     *            <code>onclick</code>, <code>onsuccess</code>, etc.
     * @return event handler script.
     */
    String getScript(String fn);

}
