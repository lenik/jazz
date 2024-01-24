package net.bodz.violet.schema.art;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.dao.ArtifactTagMapper;

public class ArtifactTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public ArtifactTag parent;

    @Override
    public ArtifactTag build()
            throws Exception {
        ArtifactTag a = new ArtifactTag();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        return a;
    }

    @Override
    public ArtifactTagSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(ArtifactTagMapper.class, "arttag");
        return this;
    }

    @Override
    public ArtifactTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
