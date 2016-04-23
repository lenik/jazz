package net.bodz.bas.http.ui.cmd;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.ui.model.action.IAction;
import net.bodz.bas.ui.model.action.RollbackException;

/**
 * @see net.bodz.bas.ui.model.action.IAction
 */
public interface IServletAction
        extends IAction {

    Object run(Object obj, IServletActionContext context)
            throws Exception;

    void rollback(Object obj, IServletActionContext context)
            throws RollbackException;

    /**
     * For synchronous action, the caller will be blocked until the action is returned.
     * 
     * @return <code>true</code> for asynchronous servlet action.
     */
    boolean isAsync();

    /**
     * @return <code>true</code> for script-only actions.
     */
    boolean isScriptOnly();

    Set<String> getScriptIds();

    /**
     * @param fn
     *            <code>onclick</code>, <code>onsuccess</code>, etc.
     * @return event handler script.
     */
    String getScript(String scriptId, Object obj);

    Map<String, String> getAttributeMap();

}
