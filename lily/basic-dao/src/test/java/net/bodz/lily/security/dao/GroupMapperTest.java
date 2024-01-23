package net.bodz.lily.security.dao;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.GroupSamples;
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
