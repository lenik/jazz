package net.bodz.bas.ui.model.action;

import java.util.List;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IActionProvider {

    /**
     * @return <code>null</code> for global actions.
     */
    Class<?> getTargetClass();

    List<IAction> getActions(Object obj);

}
