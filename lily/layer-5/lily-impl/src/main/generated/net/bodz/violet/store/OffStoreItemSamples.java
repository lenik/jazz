package net.bodz.violet.store;

import java.math.BigDecimal;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;

public class OffStoreItemSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Artifact artifact;
    public User ownerUser;

    public OffStoreItem build()
            throws Exception {
        OffStoreItem a = new OffStoreItem();
        a.setOwnerGroup(ownerGroup);
        a.setArtifact(artifact);
        a.setOwnerUser(ownerUser);
        a.setId(2464262088568220114L);
        a.setQuantity(new BigDecimal("735905633238733"));
        return a;
    }

}
