package net.bodz.violet.schema.fab;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.fab.dao.FabOrderMapper;

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
        a.setResale(false);
        a.setAltLabel("yd_qu@qoe tces");
        a.setAltSpec("e ekflhvi@ucn ute af th. o ru, ueo@ul. iq'sv oj, eyi am#bulpz eugya ax!");
        a.setAltUom("y jali iuihs_ete*Ab. j. Araxo");
        a.setQuantity(new BigDecimal("322702458.34"));
        a.setPrice(new BigDecimal("5884.12"));
        a.setAmount(new BigDecimal("83"));
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
