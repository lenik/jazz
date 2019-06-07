package net.bodz.violet.asset;

import java.math.BigDecimal;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

public class UserAssetSamples
        extends TestSamples {

    public static UserAsset build(Artifact artifact, Region region, User owner) {
        UserAsset a = new UserAsset();
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
