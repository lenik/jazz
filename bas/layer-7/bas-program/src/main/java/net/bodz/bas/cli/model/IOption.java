package net.bodz.bas.cli.model;

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

    /**
     * A short text shown with the option name. Like "FILE" for "--output=FILE".
     * 
     * value-hint is not used for boolean options.
     */
    String getValueHint();

    /**
     * Whether or not this option is shown in the help page.
     * 
     * @return <code>true</code> if this option is hidden and should not show in the help page.
     */
    boolean isHidden();

    /**
     * Whether or not this option is mandatory.
     * 
     * If a required option is not specified, an error message is displayed and the program should
     * exit immediately.
     */
    boolean isRequired();

    /**
     * Whether aliases of this option are overridable.
     * 
     * Aliases of weak-option are overridable by sub-classes.
     */
    boolean isWeak();

    /**
     * Default value is pre-parsed of default-value string.
     * 
     * If this option is not specified, the default-value is used. (However, the default-value
     * defined on the field will be overwritten.)
     * 
     * @return <code>null</code> if no default-value is defined, and the default-value on the field
     *         is used.
     */
    Object getDefaultValue();

    Class<?> getType();

    boolean isArray();

    boolean isCollection();

    boolean isMap();

    boolean isMultiple();

    Class<?> getItemType();

    Object getDefaultItemValue();

    /**
     * For boolean options, there is no parameter at all.
     * 
     * For string options, and most typed options, the parameter count is 1.
     * 
     * Special option need more parameters should override this method to return a positive integer.
     * 
     * @return Count of parameters to be consumed by this option.
     */
    int getParameterCount();

}
