package net.bodz.bas.cli.model;

import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.collection.preorder.PrefixMap;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.potato.traits.AbstractElement;
import net.bodz.bas.util.iter.Iterables;

public class DefaultOptionGroup
        extends AbstractElement
        implements IOptionGroup {

    final IOptionGroup parent;
    final Map<String, IOption> nameMap = new TreeMap<String, IOption>();
    final PrefixMap<IOption> prefixMap = new PrefixMap<IOption>();

    public DefaultOptionGroup(IOptionGroup parent, Class<?> declaringClass) {
        super(declaringClass, declaringClass.getSimpleName());
        this.parent = parent;
    }

    @Override
    public IOptionGroup getParent() {
        return parent;
    }

    @Override
    public Map<String, IOption> getOptions() {
        return nameMap;
    }

    @Override
    public IOption getOption(String optionKey) {
        return prefixMap.get(optionKey);
    }

    @Override
    public IOption getUniqueOption(String prefix)
            throws AmbiguousOptionKeyException {
        if (prefix == null)
            throw new NullPointerException("optionKeyPrefix");
        if (prefix.startsWith("no-"))
            prefix = prefix.substring(3);
        if (prefix.isEmpty())
            throw new IllegalArgumentException("prefix is empty");

        IOption option = prefixMap.get(prefix);
        if (option != null)
            return option;

        List<String> optionKeys = Iterables.toList(prefixMap.joinKeys(prefix));
        if (optionKeys.isEmpty())
            return null;

        if (optionKeys.size() > 1) {
            StringBuilder suggestions = new StringBuilder();
            for (String key : optionKeys) {
                suggestions.append(key);
                suggestions.append('\n');
            }
            throw new AmbiguousOptionKeyException(prefix, suggestions.toString());
        }
        String optionName = optionKeys.get(0);
        return prefixMap.get(optionName);
    }

    @Override
    public Map<String, IOption> findOptions(String prefix) {
        Map<String, IOption> map = new LinkedHashMap<String, IOption>();
        for (Entry<String, IOption> entry : prefixMap.joinEntries(prefix)) {
            String key = entry.getKey();
            IOption option = entry.getValue();
            map.put(key, option);
        }
        return map;
    }

    @Override
    public Set<String> getOptionKeys(IOption option) {
        Set<String> keys = new LinkedHashSet<String>();
        for (Entry<String, IOption> e : prefixMap.entrySet()) {
            if (e.getValue() != option)
                continue;
            String key = e.getKey();
            keys.add(key);
        }
        return keys;
    }

    @Override
    public void addOption(IOption option) {
        if (option == null)
            throw new NullPointerException("option");
        int priority = option.getPriority();

        // Check for conflicts
        String optionName = option.getName();
        IOption existing = nameMap.get(optionName);
        if (existing != null) {
            if (existing.getPriority() == priority)
                throw new IllegalUsageException(String.format("Option name '%s' conflicts with %s: %s.", //
                        optionName, existing.getDisplayName(), existing.getDescription()));
        }

        for (String alias : option.getAliases()) {
            IOption other = prefixMap.get(alias);
            if (other != null)
                if (other.getPriority() == option.getPriority())
                    throw new IllegalUsageException(String.format("Option alias name '%s' conflicts with %s: %s.",//
                            alias, other.getDisplayName(), other.getDescription()));
        }

        // Real add
        if (priority <= existing.getPriority()) {
            nameMap.put(optionName, option);
            prefixMap.put(optionName, option);
        }

        for (String alias : option.getAliases()) {
            IOption aliased = prefixMap.get(alias);
            if (priority <= aliased.getPriority())
                prefixMap.put(alias, option);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * (Not fully implemented)
     * 
     * @deprecated This method won't refresh/restore the old options whose option key is overrided
     *             by the given option.
     */
    @Deprecated
    @Override
    public int removeOption(IOption option) {
        int occurs = 0;
        // Remove from the name map.
        Iterator<Entry<String, IOption>> iterator = nameMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, IOption> entry = iterator.next();
            if (entry.getValue() == option) {
                iterator.remove();
                occurs++;
            }
        }

        // Remove from prefix-map.
        iterator = prefixMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, IOption> entry = iterator.next();
            if (entry.getValue() == option) {
                iterator.remove();
                occurs++;
            }
        }
        return occurs;
    }

}
