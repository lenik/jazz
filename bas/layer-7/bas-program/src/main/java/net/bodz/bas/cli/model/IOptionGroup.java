package net.bodz.bas.cli.model;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.potato.traits.IElement;

public interface IOptionGroup
        extends IElement {

    /**
     * Get the parent option group.
     * 
     * @return <code>null</code> if there's no parent.
     */
    IOptionGroup getParent();

    /**
     * Get option by canonical-name
     * 
     * @param optionKey
     *            The friendly-name or alias-name of the option.
     * @return The option with the given option key. <code>null</code> if nonexisted.
     */
    IOption getOption(String optionKey);

    /**
     * Get a unique option.
     * 
     * @return The unique option starts with the given option key. <code>null</code> if nonexisted.
     */
    IOption getUniqueOption(String optionKey)
            throws AmbiguousOptionKeyException;

    /**
     * Get all options.
     * <p>
     * The canonical-name of the option is used as the map key.
     * 
     * @return Non-<code>null</code> option map.
     */
    Map<String, IOption> getOptions();

    /**
     * Get options starts with the given prefix.
     * <p>
     * The canonical-name of the option is used as the map key.
     * 
     * @return Non-<code>null</code> option map.
     */
    Map<String, IOption> findOptions(String optionKeyPrefix);

    /**
     * Get option keys
     */
    Set<String> getOptionKeys(IOption option);

    /**
     * Add an option.
     */
    void addOption(IOption option);

    /**
     * Remove an option.
     * 
     * @return Removed occurrences from internal map.
     */
    int removeOption(IOption option);

}
