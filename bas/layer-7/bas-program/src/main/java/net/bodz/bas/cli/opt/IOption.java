package net.bodz.bas.cli.opt;

import java.util.Set;

import net.bodz.bas.potato.traits.IElement;
import net.bodz.bas.util.order.IPriority;

public interface IOption
        extends IElement, IPriority {

    /**
     * Option with same name or shortcut are overwritten by options with higher priority.
     * 
     * @return Smaller number for higher priority.
     */
    @Override
    int getPriority();

    /**
     * Divide options into separate groups.
     */
    IOptionGroup getGroup();

    /**
     * CLI name (or "cn" for short). Like "foo-bar" for "fooBar".
     * 
     * @return Non-<code>null</code> CLI name.
     */
    String getFriendlyName();

    /**
     * Shortcut names are prefixed with single dash, like "-h" for "--help".
     */
    Set<String> getAliases();

    /**
     * Get the preferred position in the arg list.
     * 
     * @return Positive integer if this option may be occurred in the arg list.
     */
    int getArgPosition();

    String getValueHint();

    boolean isHidden();

    boolean isRequired();

    boolean isWeak();

    Object getDefaultValue();

    Class<?> getType();

    boolean isArray();

    boolean isCollection();

    boolean isMap();

    boolean isMultiple();

    Class<?> getItemType();

    Object getDefaultItemValue();

    int getParameterCount();

}
