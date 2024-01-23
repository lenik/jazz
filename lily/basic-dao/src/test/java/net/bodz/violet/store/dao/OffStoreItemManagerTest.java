package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.store.OffStoreItem;
import net.bodz.violet.store.OffStoreItemSamples;

public class OffStoreItemManagerTest
        extends AbstractManagerTest<OffStoreItem, OffStoreItemMapper, OffStoreItemManager> {

    @Override
    public OffStoreItem buildSample()
            throws Exception {
        OffStoreItemSamples a = new OffStoreItemSamples();
        return a.buildWired(tables);
    }

}
