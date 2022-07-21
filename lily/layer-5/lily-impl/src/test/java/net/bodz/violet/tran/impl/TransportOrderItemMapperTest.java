package net.bodz.violet.tran.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.shop.SalesOrderItem;
import net.bodz.violet.shop.impl.SalesOrderItemMapper;
import net.bodz.violet.tran.TransportOrder;
import net.bodz.violet.tran.TransportOrderItem;
import net.bodz.violet.tran.TransportOrderItemSamples;

public class TransportOrderItemMapperTest
        extends AbstractMapperTest<TransportOrderItem, TransportOrderItemMask, TransportOrderItemMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public TransportOrderItem buildSample() {
        TransportOrder order = tables.pickAny(TransportOrderMapper.class, "tranodr");
        SalesOrderItem item = tables.pickAny(SalesOrderItemMapper.class, "saleodrl");
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        return TransportOrderItemSamples.build(order, item.getOrder(), item, artifact);
    }

}
