package net.bodz.bas.ui.model.cmd;

import java.util.List;

import net.bodz.bas.ui.dom1.IUiElement;
import net.bodz.bas.ui.model.action.IAction;

public interface ICommand
        extends IUiElement {

    /**
     * @return <code>void.class</code> if the command doesn't need a target.
     */
    Class<?> getTargetClass();

    /**
     * The command will be appeared in these locations.
     * 
     * @see net.bodz.bas.ui.model.cmd.UiLocationDecl
     */
    List<Class<?>> getLocations();

    boolean isApplicable();

    IAction getAction();

}
