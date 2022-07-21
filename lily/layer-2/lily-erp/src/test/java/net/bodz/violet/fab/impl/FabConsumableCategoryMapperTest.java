package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.fab.FabConsumableCategory;
import net.bodz.violet.fab.FabConsumableCategorySamples;

public class FabConsumableCategoryMapperTest
        extends AbstractMapperTest<FabConsumableCategory, FabConsumableCategoryMask, FabConsumableCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public FabConsumableCategory buildSample() {
        return FabConsumableCategorySamples.build();
    }

}
