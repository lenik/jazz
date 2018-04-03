package net.bodz.lily.model.base.security.impl;

import net.bodz.lily.model.base.security.Role;

public class RoleIndex
        extends CoPrincipalIndex<Role, RoleMask> {

    public static final String SCHEMA = "role";

    public RoleIndex() {
        super(SCHEMA);
    }

}
