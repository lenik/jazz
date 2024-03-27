package net.bodz.violet.schema.store;

import java.math.BigDecimal;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.store.dao.RegionMapper;

public class StoreItemSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Region region;
    public Artifact artifact;
    public Group ownerGroup;

    @Override
    public StoreItem build()
            throws Exception {
        StoreItem a = new StoreItem();
        a.setOwnerUser(ownerUser);
        a.setRegion(region);
        a.setArtifact(artifact);
        a.setOwnerGroup(ownerGroup);
        a.setQuantity(new BigDecimal("8745317.27"));
        return a;
    }

    @Override
    public StoreItemSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.region = picker.pickAny(RegionMapper.class, "region");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public StoreItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
