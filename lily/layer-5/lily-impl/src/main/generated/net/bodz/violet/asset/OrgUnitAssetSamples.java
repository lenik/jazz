package net.bodz.violet.asset;

import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

public class OrgUnitAssetSamples
        extends TestSampleBuilder {

    public OrgUnit owner;
    public User ownerUser;
    public Region region;
    public Group ownerGroup;
    public Artifact artifact;

    public OrgUnitAsset build()
            throws Exception {
        OrgUnitAsset a = new OrgUnitAsset();
        a.setOwner(owner);
        a.setOwnerUser(ownerUser);
        a.setRegion(region);
        a.setOwnerGroup(ownerGroup);
        a.setArtifact(artifact);
        return a;
    }

}
