package net.bodz.lily.security.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.Role;
import net.bodz.lily.security.RoleSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class RoleMapperTest
        extends AbstractMapperTest<Role, RoleMask, RoleMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public Role buildSample() {
        return RoleSamples.build();
    }

}
