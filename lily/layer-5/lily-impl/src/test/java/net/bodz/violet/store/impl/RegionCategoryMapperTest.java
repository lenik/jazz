package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.store.RegionCategory;
import net.bodz.violet.store.RegionCategorySamples;

public class RegionCategoryMapperTest
        extends AbstractMapperTest<RegionCategory, RegionCategoryMask, RegionCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public RegionCategory buildSample() {
        return RegionCategorySamples.build();
    }

}
