package net.bodz.violet.asset;

import java.math.BigDecimal;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.test.TestSamples;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

public class OrgAssetSamples
        extends TestSamples {

    public static OrgAsset build(Artifact artifact, Region region, Organization owner) {
        OrgAsset a = new OrgAsset();
        a.setLabel("asset-1");
        a.setDescription("A asset named asset-1.");
        a.setArtifact(artifact);
        a.setRegion(region);
        a.setOwner(owner);
        double qty = (random.nextInt() % 10000) / 100.0;
        a.setQuantity(new BigDecimal(qty));
        return a;
    }

}
