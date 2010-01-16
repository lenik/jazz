package net.bodz.bas.text.diff;

import net.bodz.bas.type.AbstractTypeTraits;

/**
 * @test {@link DiffComparatorTraitsTest}
 */
public class DiffComparatorTraits
        extends AbstractTypeTraits<DiffComparator> {

    public DiffComparatorTraits() {
        super(DiffComparator.class);
        addStaticInstances(DiffComparators.class);
    }

}
