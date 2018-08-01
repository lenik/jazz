package net.bodz.lily.security.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.GroupSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class GroupMapperTest
        extends AbstractMapperTest<Group, GroupMask, GroupMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Group buildSample() {
        return GroupSamples.build();
    }

}
