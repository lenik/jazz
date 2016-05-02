package net.bodz.bas.ui.model.action;

import java.util.Collections;
import java.util.List;

import net.bodz.bas.ctx.scope.LocalScope;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.ui.dom1.IUiElement;
import net.bodz.bas.ui.dom1.NullUiElement;

/**
 * Local scope by default.
 */
@IndexedType
@LocalScope
public interface IAction
        extends IUiElement, ILocationSpec {

    /**
     * @return <code>null</code> for global action.
     */
    Class<?> getTargetClass();

    /**
     * Determine whether the condition is fulfilled to run the action.
     */
    boolean canRun(Object obj);

    Object run(Object obj, IActionContext context)
            throws Exception;

    /**
     * Determine whether the condition is fulfilled to rollback the action.
     */
    boolean canRollback(Object obj);

    void rollback(Object obj, IActionContext context)
            throws RollbackException;

    IAction NULL = new NullAction();

}

class NullAction
        extends NullUiElement
        implements IAction {

    @Override
    public Class<?> getTargetClass() {
        return null;
    }

    @Override
    public List<? extends Class<?>> getLocations() {
        return Collections.emptyList();
    }

    @Override
    public boolean canRun(Object obj) {
        return true;
    }

    @Override
    public Object run(Object obj, IActionContext context)
            throws Exception {
        return null;
    }

    @Override
    public boolean canRollback(Object obj) {
        return true;
    }

    @Override
    public void rollback(Object obj, IActionContext context)
            throws RollbackException {
    }

}