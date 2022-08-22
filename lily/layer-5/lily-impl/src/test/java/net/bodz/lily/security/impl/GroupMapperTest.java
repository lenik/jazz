package net.bodz.lily.security.impl;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.GroupSamples;
import net.bodz.lily.security.User;
import net.bodz.lily.test.AbstractTableTest;

public class GroupMapperTest
        extends AbstractTableTest<Group, GroupMask, GroupMapper> {

    @Override
    public Group buildSample() {
        User admin = tables.pickAny(UserMapper.class, "user");
        return GroupSamples.build(admin);
    }

}
