package net.bodz.violet.store;

import net.bodz.lily.test.TestSamples;
import net.bodz.violet.art.Artifact;

public class StoreItemSamples
        extends TestSamples {

    public static StoreItem build(Artifact artifact, Region region) {
        StoreItem a = new StoreItem();
        a.setArtifact(artifact);
        a.setRegion(region);
        a.setQuantity(random.nextInt(100));
        return a;
    }

}
