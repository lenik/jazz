package net.bodz.bas.program.model;

import java.lang.reflect.Type;
import java.util.Set;

import net.bodz.bas.c.type.addor.IAddor;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.t.order.IPriority;

public interface IOption
        extends
            IElement,
            IPriority {

    /**
     * CLI name (or "cn" for short). Like "foo-bar" for "fooBar".
     *
     * @return Non-<code>null</code> CLI name.
     */
    String getPreferredLongName();

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
     * <ul>
     * <li>For boolean options, there is no parameter at all.
     * <li>For string options, and most typed options, the parameter count is 1.
     * <li>For method-call options, the parameter count is equal to the method parameter count.
     * <li>Special option need more parameters should override this method to return a positive
     * integer.
     * </ul>
     *
     * @return Count of parameters to be consumed by this option.
     */
    int getParameterCount();

    /**
     * The option type.
     */
    Class<?> getType();

    Type getGenericType();

    /**
     * Addor to specify how multiple items are combined.
     *
     * @return Non-<code>null</code> option item addor.
     */
    IAddor getAddor();

    /**
     * Type of a single option value.
     *
     * For scalar options, the value type is the same as the option type.
     *
     * @return Non-<code>null</code> option value type.
     */
    Class<?> getValueType();

    /**
     * Parse a single option value.
     *
     * @param parameters
     *            The value string to be parsed.
     * @return Parsed result.
     */
    Object parseValue(Object context, String... parameters)
            throws ParseException;

    /**
     * Format a single option value.
     *
     * @param value
     *            to be formatted.
     * @return Non-<code>null</code> formatted parameter array.
     * @throws FormatException
     *             If can't format the value.
     */
    String[] formatValue(Object context, Object value)
            throws FormatException;

    /**
     * Default value is pre-parsed of default-value string.
     *
     * If this option is not specified, the default-value is used. (However, the default-value
     * defined on the field will be overwritten.)
     *
     * For multiple-type (like collection, map), the default value is used to specify a single item
     * value.
     *
     * @return <code>null</code> if no default-value is defined, and the default-value on the field
     *         is used.
     */
    Object getDefaultValue();

    IProperty property();

}
