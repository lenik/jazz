package net.bodz.violet.schema.asset;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.contact.OrgUnit;
import net.bodz.lily.schema.contact.dao.OrgUnitMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.store.Region;
import net.bodz.violet.schema.store.dao.RegionMapper;

public class OrgUnitAssetSamples
        extends TestSampleBuilder {

    public OrgUnit owner;
    public User ownerUser;
    public Region region;
    public Group ownerGroup;
    public Artifact artifact;

    @Override
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

    @Override
    public OrgUnitAssetSamples wireAny(IRandomPicker picker) {
        this.owner = picker.pickAny(OrgUnitMapper.class, "orgunit");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.region = picker.pickAny(RegionMapper.class, "region");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        return this;
    }

    @Override
    public OrgUnitAsset buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
