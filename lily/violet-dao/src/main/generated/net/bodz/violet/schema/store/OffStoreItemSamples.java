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

public class OffStoreItemSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Artifact artifact;
    public User ownerUser;

    @Override
    public OffStoreItem build()
            throws Exception {
        OffStoreItem a = new OffStoreItem();
        a.setOwnerGroup(ownerGroup);
        a.setArtifact(artifact);
        a.setOwnerUser(ownerUser);
        a.setQuantity(new BigDecimal(".43"));
        return a;
    }

    @Override
    public OffStoreItemSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public OffStoreItem buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
