package net.bodz.lily.security.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class UserMapperTest
        extends AbstractMapperTest<User, UserMask, UserMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public User buildSample() {
        Group group = tables.pickAny(GroupMapper.class, "group");
        return UserSamples.build(group);
    }

}
