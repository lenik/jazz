package net.bodz.violet.shop.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.shop.SellPrice;
import net.bodz.violet.shop.SellPriceSamples;

public class SellPriceMapperTest
        extends AbstractTableTest<SellPrice, SellPriceMask, SellPriceMapper> {

    @Override
    public SellPrice buildSample() {
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        return SellPriceSamples.build(artifact);
    }

}
