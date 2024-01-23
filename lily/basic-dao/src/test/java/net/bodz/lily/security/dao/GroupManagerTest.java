package net.bodz.lily.security.dao;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.GroupSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class GroupManagerTest
        extends AbstractManagerTest<Group, GroupMapper, GroupManager> {

    @Override
    public Group buildSample()
            throws Exception {
        GroupSamples a = new GroupSamples();
        return a.buildWired(tables);
    }

}
