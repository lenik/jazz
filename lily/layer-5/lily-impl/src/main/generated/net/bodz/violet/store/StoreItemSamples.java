package net.bodz.violet.store;

import java.math.BigDecimal;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;

public class StoreItemSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Region region;
    public Artifact artifact;
    public Group ownerGroup;

    public StoreItem build()
            throws Exception {
        StoreItem a = new StoreItem();
        a.setOwnerUser(ownerUser);
        a.setRegion(region);
        a.setArtifact(artifact);
        a.setOwnerGroup(ownerGroup);
        a.setId(1997853094941147186L);
        a.setQuantity(new BigDecimal("27"));
        return a;
    }

}
