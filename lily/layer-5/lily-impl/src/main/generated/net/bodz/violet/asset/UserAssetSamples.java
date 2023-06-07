package net.bodz.violet.asset;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

public class UserAssetSamples
        extends TestSampleBuilder {

    public Region region;
    public User owner;
    public User ownerUser;
    public Group ownerGroup;
    public Artifact artifact;

    public UserAsset build()
            throws Exception {
        UserAsset a = new UserAsset();
        a.setRegion(region);
        a.setOwner(owner);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setArtifact(artifact);
        return a;
    }

}
