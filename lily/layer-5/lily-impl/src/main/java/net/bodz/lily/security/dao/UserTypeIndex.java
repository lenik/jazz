package net.bodz.lily.security.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.security.UserType;

/**
* @label UserType
*/
@ObjectType(UserType.class)
public class UserTypeIndex
        extends CoIndex<UserType, UserTypeMask> {

    public UserTypeIndex() {
    }

}
