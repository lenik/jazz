package net.bodz.bas.cli.nt;

public interface IOption {

    /**
     * Divide options into separate groups.
     */
    IOptionGroup getGroup();

    /**
     * CLI name (or "cn" for short). Like "foo-bar" for "fooBar".
     * 
     * @return Non-<code>null</code> CLI name.
     */
    String getCliName();

    /**
     * Shortcut names are prefixed with single dash, like "-h" for "--help".
     */
    String[] getShortcuts();

    /**
     * Option with same name or shortcut are overwritten by options with higher weight.
     * 
     * @return The greater number means higher weight.
     */
    int getWeight();

    /**
     * Minimum occurrences of this option.
     */
    int getMinOccurrence();

    /**
     * Maximum occurrences of this option.
     */
    int getMaxOccurrence();

    /**
     * Whether the parameter for an option is optional.
     */
    boolean isParamOptional();

    /**
     * Get the default value for optional parameter.
     */
    Object getParamDefault();

    int getArgumentType();

}
