package net.bodz.lily.model.base;

import java.util.Set;

import net.bodz.lily.type.ManagedEntity;

public class User
        extends ManagedEntity {

    private static final long serialVersionUID = 1L;

    String loginName;
    Set<Role> roles;

}
