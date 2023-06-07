package net.bodz.violet.store;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;

public class StoreOrderItemSamples
        extends TestSampleBuilder {

    public Artifact artifact;
    public StoreOrder order;
    public Region region;

    public StoreOrderItem build()
            throws Exception {
        StoreOrderItem a = new StoreOrderItem();
        a.setArtifact(artifact);
        a.setOrder(order);
        a.setRegion(region);
        a.setSerial(1878756156148454194L);
        a.setExpire(new Timestamp(Dates.ISO8601Z.parse("2023-01-16T04:04:27.553+0800").getTime()));
        a.setQuantity(new BigDecimal("99551921320068543.85"));
        a.setPrice(new BigDecimal("6"));
        a.setAmount(new BigDecimal("47495.16"));
        a.setNotes("diutjv'cxiu, Hiahor ou; pax ucv_qcl kugg eevqr i@fooi; nuuaga yuoa*cae@h q. idc-s! aee.");
        return a;
    }

}
