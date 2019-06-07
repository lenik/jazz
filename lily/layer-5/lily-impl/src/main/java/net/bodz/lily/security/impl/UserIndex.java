package net.bodz.lily.security.impl;

import net.bodz.lily.security.User;

public class UserIndex
        extends CoPrincipalIndex<User, UserMask> {

    public static final String SCHEMA = "user";

    public UserIndex() {
        super(SCHEMA);
    }

}
