package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.store.RegionCategory;
import net.bodz.violet.store.RegionCategorySamples;

public class RegionCategoryMapperTest
        extends AbstractMapperTest<RegionCategory, RegionCategoryMask, RegionCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public RegionCategory buildSample() {
        return RegionCategorySamples.build();
    }

}
