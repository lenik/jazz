package net.bodz.bas.cli.model;

import net.bodz.bas.i18n.dom1.IMutableElement;

public interface IMutableOptionGroup
        extends IMutableElement, IOptionGroup {

    @Override
    public IMutableOptionGroup getParent();

    /**
     * Add an option.
     */
    void addOption(IOption option)
            throws ConflictedOptionKeyException;

    /**
     * Remove an option.
     * 
     * @return Removed occurrences from internal map.
     */
    int removeOption(IOption option);

    void addUsage(SyntaxUsage usage)
            throws ConflictedUsageIdException;

    void removeUsage(SyntaxUsage usage);

}
