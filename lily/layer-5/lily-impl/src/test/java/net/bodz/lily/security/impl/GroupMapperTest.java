package net.bodz.lily.security.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.GroupSamples;
import net.bodz.lily.security.User;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class GroupMapperTest
        extends AbstractMapperTest<Group, GroupMask, GroupMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public Group buildSample() {
        User admin = tables.pickAny(UserMapper.class, "user");
        return GroupSamples.build(admin);
    }

}
