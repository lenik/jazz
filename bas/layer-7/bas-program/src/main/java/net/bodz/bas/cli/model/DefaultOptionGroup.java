package net.bodz.bas.cli.model;

import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.collection.preorder.PrefixMap;
import net.bodz.bas.potato.model.AbstractElement;
import net.bodz.bas.util.Pair;

public class DefaultOptionGroup
        extends AbstractElement
        implements IOptionGroup {

    final IOptionGroup parent;
    final Map<String, IOption> nameMap = new TreeMap<String, IOption>();
    final PrefixMap<IOption> prefixMap = new PrefixMap<IOption>();
    final Map<String, SyntaxUsage> usageMap = new LinkedHashMap<String, SyntaxUsage>();

    public DefaultOptionGroup(IOptionGroup parent, Class<?> declaringClass) {
        super(declaringClass, Strings.hyphenatize(declaringClass.getSimpleName()));
        this.parent = parent;
    }

    @Override
    public IOptionGroup getParent() {
        return parent;
    }

    @Override
    public Map<String, IOption> getLocalOptionMap() {
        return nameMap;
    }

    @Override
    public IOption getOption(String optionKey) {
        IOption option = prefixMap.get(optionKey);
        if (option != null)
            return option;
        else if (parent == null)
            return null;
        else
            return parent.getOption(optionKey);
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

        IOption option = getOption(prefix);
        if (option != null)
            return option;

        List<String> optionKeys = getSuggestKeys(prefix);
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
        String optionKey = optionKeys.get(0);
        return prefixMap.get(optionKey);
    }

    public List<String> getSuggestKeys(String optionKeyPrefix) {
        List<String> suggestKeys = new ArrayList<String>();
        fillSuggestKeys(optionKeyPrefix, suggestKeys);
        return suggestKeys;
    }

    @Override
    public void fillSuggestKeys(String prefix, Collection<String> suggestKeys) {
        if (parent != null)
            parent.fillSuggestKeys(prefix, suggestKeys);
        for (String optionKey : prefixMap.joinKeys(prefix))
            suggestKeys.add(optionKey);
    }

    public Map<String, IOption> getSuggestMap(String prefix) {
        Map<String, IOption> map = new LinkedHashMap<String, IOption>();
        fillSuggestMap(prefix, map);
        return map;
    }

    @Override
    public void fillSuggestMap(String prefix, Map<String, IOption> suggestMap) {
        if (parent != null)
            parent.fillSuggestMap(prefix, suggestMap);
        for (Entry<String, IOption> entry : prefixMap.joinEntries(prefix)) {
            String key = entry.getKey();
            IOption option = entry.getValue();
            suggestMap.put(key, option);
        }
    }

    public Set<String> getEnabledKeys(IOption option) {
        Set<String> enabledKeys = new LinkedHashSet<String>();
        fillEnabledKeys(option, enabledKeys);
        return enabledKeys;
    }

    @Override
    public void fillEnabledKeys(IOption option, Set<String> enabledKeys) {
        if (parent != null)
            parent.fillEnabledKeys(option, enabledKeys);
        for (Entry<String, IOption> e : prefixMap.entrySet())
            if (e.getValue() == option) {
                String key = e.getKey();
                enabledKeys.add(key);
            }
    }

    @Override
    public Entry<String, IOption> checkForConflict(IOption option) {
        int priority = option.getPriority();

        // Check for conflicts
        String optionName = option.getName();
        IOption existing = nameMap.get(optionName);
        if (existing != null)
            if (existing.getPriority() == priority)
                return Pair.of(optionName, existing);

        for (String alias : option.getAliases()) {
            IOption other = prefixMap.get(alias);
            if (other != null)
                if (other.getPriority() == option.getPriority())
                    return Pair.of(alias, other);
        }

        if (parent != null)
            return parent.checkForConflict(option);

        return null;
    }

    @Override
    public void addOption(IOption option) {
        if (option == null)
            throw new NullPointerException("option");

        Entry<String, IOption> conflictEntry = checkForConflict(option);
        if (conflictEntry != null)
            throw new ConflictedOptionKeyException(//
                    conflictEntry.getKey(), conflictEntry.getValue());

        // Real add
        String optionName = option.getName();
        int priority = option.getPriority();

        IOption existing = nameMap.get(optionName);
        if (existing == null || priority < existing.getPriority()) {
            nameMap.put(optionName, option);
            prefixMap.put(optionName, option);
        }

        for (String alias : option.getAliases()) {
            IOption aliased = prefixMap.get(alias);
            if (aliased != null && aliased.getPriority() < priority)
                continue;
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

        if (parent != null)
            occurs += parent.removeOption(option);

        return occurs;
    }

    @Override
    public Map<String, SyntaxUsage> getLocalUsageMap() {
        return usageMap;
    }

    public Set<String> getUsageIds() {
        Set<String> usageIds = new LinkedHashSet<String>();
        fillUsageIds(usageIds);
        return usageIds;
    }

    @Override
    public void fillUsageIds(Set<String> usageIds) {
        usageIds.addAll(usageMap.keySet());
        if (parent != null)
            parent.fillUsageIds(usageIds);
    }

    @Override
    public SyntaxUsage getUsage(String usageId) {
        SyntaxUsage usage = usageMap.get(usageId);
        if (usage != null)
            return usage;
        if (parent == null)
            return null;
        return parent.getUsage(usageId);
    }

    @Override
    public void addUsage(SyntaxUsage usage) {
        String usageId = usage.getId();
        SyntaxUsage existing = getUsage(usageId);
        if (existing != null)
            throw new ConflictedUsageIdException(usageId, existing);
        usageMap.put(usageId, usage);
    }

    @Override
    public void removeUsage(SyntaxUsage usage) {
        String usageId = usage.getId();
        usageMap.remove(usageId);
        if (parent != null)
            parent.removeUsage(usage);
    }

}
