package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.store.StoreItem;
import net.bodz.violet.store.StoreItemSamples;

public class StoreItemMapperTest
        extends AbstractMapperTest<StoreItem, StoreItemMask, StoreItemMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public StoreItem buildSample() {
        return StoreItemSamples.build();
    }

}
