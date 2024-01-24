package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.GroupType;
import net.bodz.lily.schema.account.GroupTypeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class GroupTypeMapperTest
        extends AbstractTableTest<GroupType, GroupTypeMapper> {

    @Override
    public GroupType buildSample()
            throws Exception {
        GroupTypeSamples a = new GroupTypeSamples();
        return a.buildWired(tables);
    }

}
