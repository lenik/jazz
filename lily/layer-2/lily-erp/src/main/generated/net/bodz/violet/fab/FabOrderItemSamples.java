package net.bodz.violet.fab;

import java.math.BigDecimal;

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
        a.setId(1681387801011514965L);
        a.setYear(126740905);
        a.setResale(true);
        a.setAltLabel("yd_qu@qoe tces");
        a.setAltSpec("e ekflhvi@ucn ute af th. o ru, ueo@ul. iq'sv oj, eyi am#bulpz eugya ax!");
        a.setAltUom("y jali iuihs_ete*Ab. j. Araxo");
        a.setQuantity(new BigDecimal("245883495"));
        a.setPrice(new BigDecimal("40129839149"));
        a.setAmount(new BigDecimal("7857276203"));
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
