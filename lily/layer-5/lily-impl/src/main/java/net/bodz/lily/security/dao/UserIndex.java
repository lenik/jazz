package net.bodz.lily.security.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.security.User;

/**
* @label User
*/
@ObjectType(User.class)
public class UserIndex
        extends CoIndex<User, UserCriteriaBuilder> {

    public UserIndex() {
    }

}
