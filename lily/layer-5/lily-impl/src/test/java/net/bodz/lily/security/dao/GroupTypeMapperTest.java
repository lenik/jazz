package net.bodz.lily.security.dao;

import net.bodz.lily.security.GroupType;
import net.bodz.lily.security.GroupTypeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class GroupTypeMapperTest
        extends AbstractTableTest<GroupType, GroupTypeCriteriaBuilder, GroupTypeMapper> {

    @Override
    public GroupType buildSample()
            throws Exception {
        GroupTypeSamples a = new GroupTypeSamples();
        return a.build();
    }

}
