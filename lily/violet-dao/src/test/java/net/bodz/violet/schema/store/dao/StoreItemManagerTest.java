package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.store.StoreItem;
import net.bodz.violet.schema.store.StoreItemSamples;

public class StoreItemManagerTest
        extends AbstractManagerTest<StoreItem, StoreItemMapper, StoreItemManager> {

    @Override
    public StoreItem buildSample()
            throws Exception {
        StoreItemSamples a = new StoreItemSamples();
        return a.buildWired(tables);
    }

}
