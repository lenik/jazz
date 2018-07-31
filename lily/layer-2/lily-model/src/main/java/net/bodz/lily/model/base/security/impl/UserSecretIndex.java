package net.bodz.lily.model.base.security.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;

import net.bodz.lily.model.base.security.UserSecret;
import net.bodz.lily.model.base.security.impl.UserSecretMask;

@ObjectType(UserSecret.class)
public class UserSecretIndex
        extends CoIndex<UserSecret, UserSecretMask> {

}
