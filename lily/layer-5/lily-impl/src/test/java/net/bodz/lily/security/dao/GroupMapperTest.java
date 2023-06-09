package net.bodz.lily.security.dao;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.GroupSamples;
import net.bodz.lily.test.AbstractTableTest;

public class GroupMapperTest
        extends AbstractTableTest<Group, GroupMask, GroupMapper> {

    @Override
    public Group buildSample()
            throws Exception {
        GroupSamples a = new GroupSamples();
        a.parent = tables.pickAny(GroupMapper.class, "group");
        a.type = tables.pickAny(GroupTypeMapper.class, "grouptype");
        return a.build();
    }

}
