package net.bodz.lily.model.base.security.impl;

import net.bodz.lily.model.base.security.User;

public class UserIndex
        extends CoPrincipalIndex<User, UserMask> {

    public static final String SCHEMA = "user";

    public UserIndex() {
        super(SCHEMA);
    }

}
