package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.store.StoreCategory;
import net.bodz.violet.store.StoreCategorySamples;

public class StoreCategoryManagerTest
        extends AbstractManagerTest<StoreCategory, StoreCategoryMapper, StoreCategoryManager> {

    @Override
    public StoreCategory buildSample()
            throws Exception {
        StoreCategorySamples a = new StoreCategorySamples();
        return a.buildWired(tables);
    }

}
