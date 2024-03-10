package net.bodz.violet.schema.store;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.store.dao.RegionMapper;
import net.bodz.violet.schema.store.dao.StoreOrderMapper;

public class StoreOrderItemSamples
        extends TestSampleBuilder {

    public Artifact artifact;
    public StoreOrder order;
    public Region region;

    @Override
    public StoreOrderItem build()
            throws Exception {
        StoreOrderItem a = new StoreOrderItem();
        a.setArtifact(artifact);
        a.setOrder(order);
        a.setRegion(region);
        a.setSerial(1878756156148454194L);
        a.setExpire(new Timestamp(Dates.ISO8601Z.parse("2024-01-16T04:04:27.553+0800").getTime()));
        a.setQuantity(new BigDecimal("99551921320068543.85"));
        a.setPrice(new BigDecimal("6"));
        a.setAmount(new BigDecimal("47495.16"));
        a.setNotes("diutjv'cxiu, Hiahor ou; pax ucv_qcl kugg eevqr i@fooi; nuuaga yuoa*cae@h q. idc-s! aee.");
        return a;
    }

    @Override
    public StoreOrderItemSamples wireAny(IRandomPicker picker) {
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.order = picker.pickAny(StoreOrderMapper.class, "storeodr");
        this.region = picker.pickAny(RegionMapper.class, "region");
        return this;
    }

    @Override
    public StoreOrderItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
