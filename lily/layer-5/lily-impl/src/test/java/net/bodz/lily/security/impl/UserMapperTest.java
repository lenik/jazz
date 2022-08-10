package net.bodz.lily.security.impl;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class UserMapperTest
        extends AbstractMapperTest<User, UserMask, UserMapper> {

    @Override
    public User buildSample() {
        Group group = tables.pickAny(GroupMapper.class, "group");
        return UserSamples.build(group);
    }

}
