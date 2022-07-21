package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.store.StoreCategory;
import net.bodz.violet.store.StoreCategorySamples;

public class StoreCategoryMapperTest
        extends AbstractMapperTest<StoreCategory, StoreCategoryMask, StoreCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public StoreCategory buildSample() {
        return StoreCategorySamples.build();
    }

}
