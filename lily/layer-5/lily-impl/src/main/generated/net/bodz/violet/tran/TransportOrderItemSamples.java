package net.bodz.violet.tran;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;

public class TransportOrderItemSamples
        extends TestSampleBuilder {

    public Artifact artifact;
    public TransportOrder order;

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

}
