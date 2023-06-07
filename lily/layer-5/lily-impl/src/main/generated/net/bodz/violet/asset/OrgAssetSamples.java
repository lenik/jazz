package net.bodz.violet.asset;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

public class OrgAssetSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public Organization owner;
    public Region region;
    public Artifact artifact;

    public OrgAsset build()
            throws Exception {
        OrgAsset a = new OrgAsset();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setOwner(owner);
        a.setRegion(region);
        a.setArtifact(artifact);
        return a;
    }

}
