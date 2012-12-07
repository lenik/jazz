package net.bodz.bas.cli.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.bodz.bas.cli.skel.CLISyntaxException;
import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.model1.ArtifactObject;

public class ArtifactObjectWithOptions
        extends ArtifactObject
        implements IOptionGroup {

    private transient IOptionGroup optionGroup;

    public ArtifactObjectWithOptions() {
    }

    public synchronized IOptionGroup getOptionGroup() {
        if (optionGroup == null)
            optionGroup = OptionGroupFactory.getClassOptions(getClass());
        return optionGroup;
    }

    @Override
    public IOptionGroup getParent() {
        return getOptionGroup().getParent();
    }

    @Override
    public Map<String, IOption> getLocalOptionMap() {
        return getOptionGroup().getLocalOptionMap();
    }

    @Override
    public IOption getOption(String optionKey) {
        return getOptionGroup().getOption(optionKey);
    }

    @Override
    public IOption getUniqueOption(String optionKey)
            throws AmbiguousOptionKeyException {
        return getOptionGroup().getUniqueOption(optionKey);
    }

    @Override
    public void fillSuggestKeys(String optionKeyPrefix, Collection<String> suggestions) {
        getOptionGroup().fillSuggestKeys(optionKeyPrefix, suggestions);
    }

    @Override
    public void fillSuggestMap(String optionKeyPrefix, Map<String, IOption> results) {
        getOptionGroup().fillSuggestMap(optionKeyPrefix, results);
    }

    @Override
    public void fillEnabledKeys(IOption option, Set<String> enabledKeys) {
        getOptionGroup().fillEnabledKeys(option, enabledKeys);
    }

    @Override
    public Entry<String, IOption> checkForConflict(IOption option) {
        return getOptionGroup().checkForConflict(option);
    }

    @Override
    public Map<String, SyntaxUsage> getLocalUsageMap() {
        return getOptionGroup().getLocalUsageMap();
    }

    @Override
    public void fillUsageIds(Set<String> usageIds) {
        getOptionGroup().fillUsageIds(usageIds);
    }

    @Override
    public SyntaxUsage getUsage(String usageId) {
        return getOptionGroup().getUsage(usageId);
    }

    @Override
    public List<String> accept(OptionGroupParseFlags flags, Object context, String... args)
            throws CLISyntaxException, ParseException {
        return getOptionGroup().accept(flags, context, args);
    }

    public List<String> accept(String... args)
            throws CLISyntaxException, ParseException {
        List<String> rejected = accept(OptionGroupParseFlags.DEFAULT, this, args);
        return rejected;
    }

}