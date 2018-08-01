package net.bodz.lily.security.impl;

import net.bodz.lily.security.Role;

public class RoleIndex
        extends CoPrincipalIndex<Role, RoleMask> {

    public static final String SCHEMA = "role";

    public RoleIndex() {
        super(SCHEMA);
    }

}
