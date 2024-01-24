package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.GroupType;
import net.bodz.lily.schema.account.GroupTypeSamples;
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
