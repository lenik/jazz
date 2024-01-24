package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.store.StoreCategory;
import net.bodz.violet.schema.store.StoreCategorySamples;

public class StoreCategoryManagerTest
        extends AbstractManagerTest<StoreCategory, StoreCategoryMapper, StoreCategoryManager> {

    @Override
    public StoreCategory buildSample()
            throws Exception {
        StoreCategorySamples a = new StoreCategorySamples();
        return a.buildWired(tables);
    }

}
