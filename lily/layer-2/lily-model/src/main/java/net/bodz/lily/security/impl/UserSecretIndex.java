package net.bodz.lily.security.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;

import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.impl.UserSecretMask;

@ObjectType(UserSecret.class)
public class UserSecretIndex
        extends CoIndex<UserSecret, UserSecretMask> {

}
