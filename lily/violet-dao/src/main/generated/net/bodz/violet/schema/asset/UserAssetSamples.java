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

public class UserAssetSamples
        extends TestSampleBuilder {

    public Region region;
    public User owner;
    public User ownerUser;
    public Group ownerGroup;
    public Artifact artifact;

    @Override
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

    @Override
    public UserAssetSamples wireAny(IRandomPicker picker) {
        this.region = picker.pickAny(RegionMapper.class, "region");
        this.owner = picker.pickAny(UserMapper.class, "user");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        return this;
    }

    @Override
    public UserAsset buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
