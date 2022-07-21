package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.StoreOrder;
import net.bodz.violet.store.StoreOrderItem;
import net.bodz.violet.store.StoreOrderItemSamples;

public class StoreOrderItemMapperTest
        extends AbstractMapperTest<StoreOrderItem, StoreOrderItemMask, StoreOrderItemMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public StoreOrderItem buildSample() {
        StoreOrder order = tables.pickAny(StoreOrderMapper.class, "storeodr");
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        Region region = tables.pickAny(RegionMapper.class, "region");
        return StoreOrderItemSamples.build(order, artifact, region);
    }

}
