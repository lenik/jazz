package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.store.StoreCategory;
import net.bodz.violet.schema.store.StoreCategorySamples;

public class StoreCategoryMapperTest
        extends AbstractTableTest<StoreCategory, StoreCategoryMapper> {

    @Override
    public StoreCategory buildSample()
            throws Exception {
        StoreCategorySamples a = new StoreCategorySamples();
        return a.buildWired(tables);
    }

}
