package net.bodz.lily.security.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;

import net.bodz.lily.security.UserIdType;
import net.bodz.lily.security.impl.UserIdTypeMask;

@ObjectType(UserIdType.class)
public class UserIdTypeIndex
        extends CoIndex<UserIdType, UserIdTypeMask> {

}
