package net.bodz.lily.security.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.security.UserSecret;

/**
* @label UserSecret
*/
@ObjectType(UserSecret.class)
public class UserSecretIndex
        extends CoIndex<UserSecret, UserSecretMask> {

    public UserSecretIndex() {
    }

}