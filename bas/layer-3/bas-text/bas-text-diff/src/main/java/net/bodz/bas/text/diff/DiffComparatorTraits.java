package net.bodz.bas.text.diff;

import net.bodz.bas.traits.AbstractCommonTraits;

public class DiffComparatorTraits
        extends AbstractCommonTraits<DiffComparator> {

    public DiffComparatorTraits() {
        super(DiffComparator.class);
        addStaticInstances(DiffComparators.class);
    }

}
