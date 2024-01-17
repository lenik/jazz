package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.StoreItem;
import net.bodz.violet.store.StoreItemSamples;

public class StoreItemMapperTest
        extends AbstractTableTest<StoreItem, StoreItemMapper> {

    @Override
    public StoreItem buildSample()
            throws Exception {
        StoreItemSamples a = new StoreItemSamples();
        return a.buildWired(tables);
    }

}
