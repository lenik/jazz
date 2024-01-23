package net.bodz.violet.fab;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.fab.dao.FabOrderMapper;

public class FabOrderItemSamples
        extends TestSampleBuilder {

    public Artifact artifact;
    public FabOrder order;

    @Override
    public FabOrderItem build()
            throws Exception {
        FabOrderItem a = new FabOrderItem();
        a.setArtifact(artifact);
        a.setOrder(order);
        a.setId(1681387801011514965L);
        a.setBeginTime(ZonedDateTime.parse("2023-12-30 10:44:01", DateTimes.D10T8));
        a.setEndTime(ZonedDateTime.parse("2023-12-30 13:47:39", DateTimes.D10T8));
        a.setYear(855797600);
        a.setResale(false);
        a.setAltLabel("yd_qu@qoe tces");
        a.setAltSpec("e ekflhvi@ucn ute af th. o ru, ueo@ul. iq'sv oj, eyi am#bulpz eugya ax!");
        a.setAltUom("y jali iuihs_ete*Ab. j. Araxo");
        a.setQuantity(new BigDecimal("5883495884012.83"));
        a.setPrice(new BigDecimal("1491807.57"));
        a.setAmount(new BigDecimal("762"));
        a.setNotes("st? x; pq, juueh-rp*ja, uuxp");
        return a;
    }

    @Override
    public FabOrderItemSamples wireAny(IRandomPicker picker) {
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.order = picker.pickAny(FabOrderMapper.class, "fabodr");
        return this;
    }

    @Override
    public FabOrderItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
