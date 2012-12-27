package net.bodz.bas.program.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.program.skel.CLISyntaxException;

public interface IOptionGroup
        extends IElement {

    /**
     * Get the parent option group.
     * 
     * @return <code>null</code> if there's no parent.
     */
    IOptionGroup getParent();

    /**
     * Get local options.
     * 
     * The inherited options from parent option group is not included.
     * 
     * <p>
     * The canonical-name of the option is used as the map key.
     * 
     * @return Non-<code>null</code> local option map.
     */
    Map<String, IOption> getLocalOptionMap();

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

    void fillSuggestKeys(String optionKeyPrefix, Collection<String> suggestions);

    /**
     * Get options starts with the given prefix.
     * <p>
     * The canonical-name of the option is used as the map key.
     * 
     * @param optionKeyPrefix
     *            The prefix of option keys to find.
     * @param results
     *            Non-<code>null</code> option map to fill with matching results.
     */
    void fillSuggestMap(String optionKeyPrefix, Map<String, IOption> results);

    /**
     * Get enabled option keys
     */
    void fillEnabledKeys(IOption option, Set<String> enabledKeys);

    /**
     * Find and return a conflicted option with the question key.
     * 
     * @return The first conflict. The entry contains the conflicted option key and the conflicted
     *         option. Returns <code>null</code> if no conflict.
     */
    Entry<String, IOption> checkForConflict(IOption option);

    /**
     * Get syntax usage map.
     * 
     * @return Non-<code>null</code> usage map: id => {@link SyntaxUsage}.
     */
    Map<String, SyntaxUsage> getLocalUsageMap();

    /**
     * Get all syntax usage ids;
     * 
     * @param usageIds
     *            Non-<code>null</code> string set to be filled with usage ids.
     */
    void fillUsageIds(Set<String> usageIds);

    /**
     * Get syntax usage by id.
     * 
     * @return The syntax usage with specified id.
     */
    SyntaxUsage getUsage(String usageId);

    /**
     * @return List of rejected arguments.
     */
    List<String> receive(Object receiver, String[] args, OptionGroupParseFlags flags)
            throws CLISyntaxException, ParseException;

}
