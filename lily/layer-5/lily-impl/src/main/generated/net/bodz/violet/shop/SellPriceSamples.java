package net.bodz.violet.shop;

import java.math.BigDecimal;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;

public class SellPriceSamples
        extends TestSampleBuilder {

    public Artifact artifact;
    public Group ownerGroup;
    public User ownerUser;

    public SellPrice build()
            throws Exception {
        SellPrice a = new SellPrice();
        a.setArtifact(artifact);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setId(165217238);
        a.setCode("aamt.");
        a.setPrice(new BigDecimal("88.34"));
        return a;
    }

}
