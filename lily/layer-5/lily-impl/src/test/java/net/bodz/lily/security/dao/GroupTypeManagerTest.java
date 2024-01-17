package net.bodz.lily.security.dao;

import net.bodz.lily.security.GroupType;
import net.bodz.lily.security.GroupTypeSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class GroupTypeManagerTest
        extends AbstractManagerTest<GroupType, GroupTypeMapper, GroupTypeManager> {

    @Override
    public GroupType buildSample()
            throws Exception {
        GroupTypeSamples a = new GroupTypeSamples();
        return a.buildWired(tables);
    }

}
