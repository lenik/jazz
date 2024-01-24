package net.bodz.violet.schema.tran;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.tran.dao.TransportOrderMapper;

public class TransportOrderItemSamples
        extends TestSampleBuilder {

    public Artifact artifact;
    public TransportOrder order;

    @Override
    public TransportOrderItem build()
            throws Exception {
        TransportOrderItem a = new TransportOrderItem();
        a.setArtifact(artifact);
        a.setOrder(order);
        a.setQuantity(new BigDecimal("6014329825"));
        a.setPrice(new BigDecimal("220588930"));
        a.setAmount(new BigDecimal("86062751.05"));
        return a;
    }

    @Override
    public TransportOrderItemSamples wireAny(IRandomPicker picker) {
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.order = picker.pickAny(TransportOrderMapper.class, "tranodr");
        return this;
    }

    @Override
    public TransportOrderItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
