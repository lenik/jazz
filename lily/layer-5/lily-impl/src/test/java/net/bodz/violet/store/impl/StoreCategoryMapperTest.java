package net.bodz.violet.store.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.StoreCategory;
import net.bodz.violet.store.StoreCategorySamples;

public class StoreCategoryMapperTest
        extends AbstractTableTest<StoreCategory, StoreCategoryMask, StoreCategoryMapper> {

    @Override
    public StoreCategory buildSample() {
        return StoreCategorySamples.build();
    }

}
