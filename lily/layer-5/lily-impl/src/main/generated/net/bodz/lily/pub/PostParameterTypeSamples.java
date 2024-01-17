package net.bodz.lily.pub;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostParameterTypeSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    @Override
    public PostParameterType build()
            throws Exception {
        PostParameterType a = new PostParameterType();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setDummy(329238087);
        return a;
    }

    @Override
    public PostParameterTypeSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public PostParameterType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
