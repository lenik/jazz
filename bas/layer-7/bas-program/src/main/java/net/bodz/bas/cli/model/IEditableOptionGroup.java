package net.bodz.bas.cli.model;

import net.bodz.bas.i18n.dom1.IEditableElement;

public interface IEditableOptionGroup
        extends IEditableElement, IOptionGroup {

    @Override
    public IEditableOptionGroup getParent();

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
