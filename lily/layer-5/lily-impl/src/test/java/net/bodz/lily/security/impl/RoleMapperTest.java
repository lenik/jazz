package net.bodz.lily.security.impl;

import net.bodz.lily.security.Role;
import net.bodz.lily.security.RoleSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class RoleMapperTest
        extends AbstractMapperTest<Role, RoleMask, RoleMapper> {

    @Override
    public Role buildSample() {
        return RoleSamples.build();
    }

}
