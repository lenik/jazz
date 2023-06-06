package net.bodz.lily.security.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.security.UserOtherId;

/**
* @label UserOtherId
*/
@ObjectType(UserOtherId.class)
public class UserOtherIdIndex
        extends CoIndex<UserOtherId, UserOtherIdMask> {

    public UserOtherIdIndex() {
    }

}
