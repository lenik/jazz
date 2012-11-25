package net.bodz.bas.cli.model;

import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.collection.preorder.PrefixMap;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.util.Pair;
import net.bodz.mda.xjdoc.model1.ArtifactDoc;

public class TransientOptionGroup
        extends AbstractOptionGroup
        implements IEditableOptionGroup {

    private static final long serialVersionUID = 1L;

    Class<?> declaringClass;
    String name;

    final IEditableOptionGroup parent;
    final Map<String, IOption> nameMap = new TreeMap<String, IOption>();
    final PrefixMap<IOption> prefixMap = new PrefixMap<IOption>();
    final Map<String, SyntaxUsage> usageMap = new LinkedHashMap<String, SyntaxUsage>();

    public TransientOptionGroup(IEditableOptionGroup parent, Class<?> declaringClass) {
        this.declaringClass = declaringClass;
        this.name = Strings.hyphenatize(declaringClass.getSimpleName());
        this.parent = parent;
    }

    @Override
    public ArtifactDoc getArtifactDoc() {
        throw new NotImplementedException();
    }

    @Override
    protected IOption getLocalOption(String optionKey) {
        return prefixMap.get(optionKey);
    }

    @Override
    public IEditableOptionGroup getParent() {
        return parent;
    }

    @Override
    public Map<String, IOption> getLocalOptionMap() {
        return nameMap;
    }

    @Override
    public void fillSuggestKeys(String prefix, Collection<String> suggestKeys) {
        if (parent != null)
            parent.fillSuggestKeys(prefix, suggestKeys);
        for (String optionKey : prefixMap.joinKeys(prefix))
            suggestKeys.add(optionKey);
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
