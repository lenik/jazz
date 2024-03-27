package net.bodz.violet.schema.store;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import net.bodz.bas.c.java.util.DateTimes;
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
        a.setExpire(OffsetDateTime.parse("2024-01-15T23:24:27.553-04:40", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setQuantity(new BigDecimal("955192132006.54"));
        a.setPrice(new BigDecimal("85267204749"));
        a.setAmount(new BigDecimal("163328609327945331.99"));
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
