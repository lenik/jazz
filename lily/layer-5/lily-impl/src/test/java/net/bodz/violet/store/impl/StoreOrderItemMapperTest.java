package net.bodz.violet.store.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.StoreOrder;
import net.bodz.violet.store.StoreOrderItem;
import net.bodz.violet.store.StoreOrderItemSamples;

public class StoreOrderItemMapperTest
        extends AbstractTableTest<StoreOrderItem, StoreOrderItemMask, StoreOrderItemMapper> {

    @Override
    public StoreOrderItem buildSample() {
        StoreOrder order = tables.pickAny(StoreOrderMapper.class, "storeodr");
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        Region region = tables.pickAny(RegionMapper.class, "region");
        return StoreOrderItemSamples.build(order, artifact, region);
    }

}
