package net.bodz.lily.security.impl;

import net.bodz.lily.security.Role;
import net.bodz.lily.security.RoleSamples;
import net.bodz.lily.test.AbstractTableTest;

public class RoleMapperTest
        extends AbstractTableTest<Role, RoleMask, RoleMapper> {

    @Override
    public Role buildSample() {
        return RoleSamples.build();
    }

}
