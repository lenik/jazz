package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.store.StoreOrderItem;
import net.bodz.violet.store.StoreOrderItemSamples;

public class StoreOrderItemMapperTest
        extends AbstractTableTest<StoreOrderItem, StoreOrderItemMask, StoreOrderItemMapper> {

    @Override
    public StoreOrderItem buildSample()
            throws Exception {
        StoreOrderItemSamples a = new StoreOrderItemSamples();
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        a.order = tables.pickAny(StoreOrderMapper.class, "storeodr");
        a.region = tables.pickAny(RegionMapper.class, "region");
        return a.build();
    }

}
