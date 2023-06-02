package net.bodz.lily.security.impl;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.GroupSamples;
import net.bodz.lily.test.AbstractTableTest;

public class GroupMapperTest
        extends AbstractTableTest<Group, GroupMask, GroupMapper> {

    @Override
    public Group buildSample() {
        Group parent = tables.pickAny(GroupMapper.class, "group");
        return GroupSamples.build(parent);
    }

}
