package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.store.OffStoreItem;
import net.bodz.violet.schema.store.OffStoreItemSamples;

public class OffStoreItemMapperTest
        extends AbstractTableTest<OffStoreItem, OffStoreItemMapper> {

    @Override
    public OffStoreItem buildSample()
            throws Exception {
        OffStoreItemSamples a = new OffStoreItemSamples();
        return a.buildWired(tables);
    }

}
