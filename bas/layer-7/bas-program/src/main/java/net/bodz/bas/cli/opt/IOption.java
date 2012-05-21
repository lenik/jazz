package net.bodz.bas.cli.opt;

import java.util.Set;

import net.bodz.bas.potato.traits.IElement;
import net.bodz.bas.util.order.IPriority;

interface IOption
        extends IElement, IPriority {

    /**
     * Option with same name or shortcut are overwritten by options with higher priority.
     * 
     * @return Smaller number for higher priority.
     */
    @Override
    int getPriority();

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

    int getFileIndex();

    /**
     * Divide options into separate groups.
     */
    IOptionGroup getGroup();

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
