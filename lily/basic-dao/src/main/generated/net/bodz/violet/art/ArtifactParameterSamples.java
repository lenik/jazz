package net.bodz.violet.art;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArtifactParameterSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    @Override
    public ArtifactParameter build()
            throws Exception {
        ArtifactParameter a = new ArtifactParameter();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setDummy(350540066);
        return a;
    }

    @Override
    public ArtifactParameterSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public ArtifactParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
