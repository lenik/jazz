package net.bodz.violet.schema.asset;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.store.Region;
import net.bodz.violet.schema.store.dao.RegionMapper;

public class GroupAssetSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public Group owner;
    public Artifact artifact;
    public Region region;

    @Override
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

    @Override
    public GroupAssetSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.owner = picker.pickAny(GroupMapper.class, "group");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.region = picker.pickAny(RegionMapper.class, "region");
        return this;
    }

    @Override
    public GroupAsset buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
