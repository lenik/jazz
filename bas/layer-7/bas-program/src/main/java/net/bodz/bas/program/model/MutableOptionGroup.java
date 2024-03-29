package net.bodz.bas.program.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import net.bodz.bas.c.string.StringId;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.preorder.PrefixMap;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class MutableOptionGroup
        extends AbstractOptionGroup
        implements
            IMutableOptionGroup {

    private static final long serialVersionUID = 1L;

    Class<?> declaringClass;

    final IMutableOptionGroup parent;
    final Map<String, IOption> nameMap = new TreeMap<String, IOption>();
    final PrefixMap<IOption> prefixMap = new PrefixMap<IOption>();
    final Map<String, SyntaxUsage> usageMap = new LinkedHashMap<String, SyntaxUsage>();

    public MutableOptionGroup(IMutableOptionGroup parent, Class<?> declaringClass) {
        this.declaringClass = declaringClass;
        this.parent = parent;
        String name = StringId.HYPHEN.breakCamel(declaringClass.getSimpleName());
        setName(name);
    }

    @Override
    protected IElementDoc loadXjdoc() {
        ClassDoc classDoc = Xjdocs.getDefaultProvider().getClassDoc(declaringClass);
        return classDoc;
    }

    @Override
    protected IOption getLocalOption(String optionKey) {
        return prefixMap.get(optionKey);
    }

    @Override
    public void removeKey(String key) {
        prefixMap.remove(key);
    }

    @Override
    public IMutableOptionGroup getParent() {
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
    public OptionConflictInfo checkForConflict(IOption option) {
        String optionName = option.getName();
        IOption existing = nameMap.get(optionName);
        if (existing != null) {
            return new OptionConflictInfo(optionName, existing);
        }

        for (String alias : option.getAliases()) {
            IOption other = prefixMap.get(alias);
            if (other != null)
                if (other.getPriority() == option.getPriority())
                    return new OptionConflictInfo(alias, other);
        }

        if (parent != null)
            return parent.checkForConflict(option);

        return null;
    }

    @Override
    public void addOption(IOption option) {
        if (option == null)
            throw new NullPointerException("option");

        OptionConflictInfo info = checkForConflict(option);
        if (info != null) {
            IOption prev = info.preexisted;
            IOptionGroup prevGroup = prev.getGroup();
            if (prevGroup == this)
                throw new ConflictedOptionKeyException(//
                        info.aliases.toString(), info.preexisted);

            // override the alias to point to the new option.
            if (prev instanceof IMutableOption) {
                IMutableOption mutable = (IMutableOption) prev;
                for (String alias : info.aliases)
                    mutable.removeAlias(alias);
            }
            if (prevGroup instanceof IMutableOptionGroup) {
                IMutableOptionGroup mutable = (IMutableOptionGroup) prevGroup;
                for (String alias : info.aliases)
                    mutable.removeKey(alias);
            }
        }

        // Real add
        String optionName = option.getName();
        int priority = option.getPriority();

        assert !nameMap.containsKey(optionName);
        nameMap.put(optionName, option);

        for (String alias : option.getAliases()) {
            IOption prev = prefixMap.get(alias);
            if (prev != null && prev.getPriority() < priority)
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

    /** ⇱ Implementaton Of {@link net.bodz.bas.i18n.dom1.IMutableElement}. */
    /* _____________________________ */static section.iface __MUTABLE__;

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setLabel(iString label) {
        super.setLabel(label);
    }

    @Override
    public void setDescription(iString description) {
        super.setDescription(description);
    }

    @Override
    public void setHelpDoc(iString helpDoc) {
        super.setHelpDoc(helpDoc);
    }

    @Override
    public void setDetailLevel(int detailLevel) {
        super.setDetailLevel(detailLevel);
    }

    @Override
    public void setModifiers(int modifiers) {
        super.setModifiers(modifiers);
    }

    @Override
    public void setPriority(int priority) {
        super.setPriority(priority);
    }

}
