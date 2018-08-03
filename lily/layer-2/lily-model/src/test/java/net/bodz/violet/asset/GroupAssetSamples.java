package net.bodz.violet.asset;

import java.math.BigDecimal;
import java.util.Random;

import net.bodz.lily.security.Group;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

public class GroupAssetSamples {

    static Random random = new Random();

    public static GroupAsset build(Artifact artifact, Region region, Group owner) {
        GroupAsset a = new GroupAsset();
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
