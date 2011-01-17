package net.bodz.bas.text.diff;

import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IInstanceStore;

public class DiffComparatorTraits
        extends AbstractCommonTraits<DiffComparator> {

    public DiffComparatorTraits() {
        super(DiffComparator.class);
        addStaticInstances(DiffComparators.class);
    }

    @Override
    public IInstanceStore<DiffComparator> getInstanceStore() {
        return this;
    }

}
