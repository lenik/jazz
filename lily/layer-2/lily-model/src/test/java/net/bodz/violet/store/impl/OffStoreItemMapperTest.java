package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.store.OffStoreItem;
import net.bodz.violet.store.OffStoreItemSamples;

public class OffStoreItemMapperTest
        extends AbstractMapperTest<OffStoreItem, OffStoreItemMask, OffStoreItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public OffStoreItem buildSample() {
        return OffStoreItemSamples.build();
    }

}
