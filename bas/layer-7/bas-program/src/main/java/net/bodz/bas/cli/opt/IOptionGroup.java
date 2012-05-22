package net.bodz.bas.cli.opt;

import java.util.Map;

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
     * Get option by canonical-name, friendly-name or alias-name.
     * 
     * The option may be bound to different kind of names:
     * <ul>
     * <li>Canonical name: help, outputDirectory
     * <li>Friendly name: help, output-directory
     * <li>Alias name: h, O
     * </ul>
     * 
     * @param key
     *            The name or alias of the option.
     * @param canonicalForm
     *            Specify what kind of key is used, <code>true</code> for canonical-name,
     *            <code>false</code> for friendly-name or alias-name.
     * @return Option for the key, <code>null</code> if the key isn't defined.
     */
    IOption getOption(String key, boolean canonicalForm);

    /**
     * The same as {@link #getOption(String, boolean)} with <code>canonicalForm</code> set to
     * <code>false</code>.
     * 
     * @param key
     *            The friendly-name or alias-name of the option.
     * @return Option for the key, <code>null</code> if the key isn't defined.
     */
    IOption getOption(String key);

    /**
     * Get all options. The canonical-name of the option is used as the map key.
     * 
     * @return Non-<code>null</code> option map.
     */
    Map<String, IOption> getOptions();

    Map<String, IOption> findOptions(String prefix);

}
