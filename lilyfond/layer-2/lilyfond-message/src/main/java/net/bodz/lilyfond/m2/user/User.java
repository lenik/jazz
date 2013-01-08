package net.bodz.lilyfond.m2.user;

import java.util.Set;

import net.bodz.lilyfond.m2.ManagedEntity;

public class User
        extends ManagedEntity {

    private static final long serialVersionUID = 1L;

    String loginName;
    Set<Role> roles;

}
