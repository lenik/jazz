package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.fab.FabConsumableCategory;
import net.bodz.violet.fab.FabConsumableCategorySamples;

public class FabConsumableCategoryMapperTest
        extends AbstractMapperTest<FabConsumableCategory, FabConsumableCategoryMask, FabConsumableCategoryMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public FabConsumableCategory buildSample() {
        return FabConsumableCategorySamples.build();
    }

}
