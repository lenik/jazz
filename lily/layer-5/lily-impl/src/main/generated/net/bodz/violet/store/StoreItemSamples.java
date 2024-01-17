package net.bodz.violet.store;

import java.math.BigDecimal;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.store.dao.RegionMapper;

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
        a.setId(1997853094941147186L);
        a.setQuantity(new BigDecimal("27"));
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
