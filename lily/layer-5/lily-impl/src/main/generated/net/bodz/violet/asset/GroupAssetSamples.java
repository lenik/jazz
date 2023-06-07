package net.bodz.violet.asset;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

public class GroupAssetSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public Group owner;
    public Artifact artifact;
    public Region region;

    public GroupAsset build()
            throws Exception {
        GroupAsset a = new GroupAsset();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setOwner(owner);
        a.setArtifact(artifact);
        a.setRegion(region);
        return a;
    }

}
