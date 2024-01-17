package net.bodz.violet.tran.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.tran.TransportOrderItem;
import net.bodz.violet.tran.TransportOrderItemSamples;

public class TransportOrderItemMapperTest
        extends AbstractTableTest<TransportOrderItem, TransportOrderItemCriteriaBuilder, TransportOrderItemMapper> {

    @Override
    public TransportOrderItem buildSample()
            throws Exception {
        TransportOrderItemSamples a = new TransportOrderItemSamples();
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        a.order = tables.pickAny(TransportOrderMapper.class, "tranodr");
        return a.build();
    }

}
