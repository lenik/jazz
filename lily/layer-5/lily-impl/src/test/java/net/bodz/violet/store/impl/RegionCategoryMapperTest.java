package net.bodz.violet.store.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.RegionCategory;
import net.bodz.violet.store.RegionCategorySamples;

public class RegionCategoryMapperTest
        extends AbstractTableTest<RegionCategory, RegionCategoryMask, RegionCategoryMapper> {

    @Override
    public RegionCategory buildSample() {
        return RegionCategorySamples.build();
    }

}
