package net.bodz.bas.text.diff;

import net.bodz.bas.traits.AbstractCommonTraits;

/**
 * @test {@link DiffComparatorTraitsTest}
 */
public class DiffComparatorTraits
        extends AbstractCommonTraits<DiffComparator> {

    public DiffComparatorTraits() {
        super(DiffComparator.class);
        addStaticInstances(DiffComparators.class);
    }

}
