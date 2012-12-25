package net.bodz.bas.text.diff;

import net.bodz.bas.traits.AbstractCommonTraits;

public class DiffComparatorTraits
        extends AbstractCommonTraits<IDiffComparator> {

    public DiffComparatorTraits() {
        super(IDiffComparator.class);
        addStaticFieldsToStore(DiffComparators.class);
    }

    @Override
    protected Object query(int traitsIndex) {
        return null;
    }

}
