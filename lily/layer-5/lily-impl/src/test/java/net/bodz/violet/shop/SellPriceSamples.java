package net.bodz.violet.shop;

import net.bodz.lily.test.TestSamples;
import net.bodz.violet.art.Artifact;

public class SellPriceSamples
        extends TestSamples {

    public static SellPrice build(Artifact artifact) {
        SellPrice a = new SellPrice();
        a.setArtifact(artifact);
        a.setPrice(random.nextInt(10000) / 100.0);
        return a;
    }

}
