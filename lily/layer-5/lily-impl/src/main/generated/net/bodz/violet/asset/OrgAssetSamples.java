package net.bodz.violet.asset;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.dao.RegionMapper;

public class OrgAssetSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public Organization owner;
    public Region region;
    public Artifact artifact;

    @Override
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

    @Override
    public OrgAssetSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.owner = picker.pickAny(OrganizationMapper.class, "org");
        this.region = picker.pickAny(RegionMapper.class, "region");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        return this;
    }

    @Override
    public OrgAsset buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
