package net.bodz.lily.model.base.security.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;

import net.bodz.lily.model.base.security.UserId;
import net.bodz.lily.model.base.security.impl.UserIdMask;

@ObjectType(UserId.class)
public class UserIdIndex
        extends CoIndex<UserId, UserIdMask> {

}
