package net.bodz.violet.asset;

import java.math.BigDecimal;
import java.util.Random;

import net.bodz.lily.contact.Person;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

public class PersonAssetSamples {

    static Random random = new Random();

    public static PersonAsset build(Artifact artifact, Region region, Person owner) {
        PersonAsset a = new PersonAsset();
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
