package net.bodz.lily.security.impl;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserMapperTest
        extends AbstractTableTest<User, UserMask, UserMapper> {

    @Override
    public User buildSample() {
        Group group = tables.pickAny(GroupMapper.class, "group");
        return UserSamples.build(group);
    }

}
