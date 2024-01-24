package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.GroupSamples;
import net.bodz.lily.test.AbstractTableTest;

public class GroupMapperTest
        extends AbstractTableTest<Group, GroupMapper> {

    @Override
    public Group buildSample()
            throws Exception {
        GroupSamples a = new GroupSamples();
        return a.buildWired(tables);
    }

}
