package net.bodz.lily.schema.account.ws;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.concrete.CoIndex;
import net.bodz.lily.schema.account.User;

/**
 * @label User
 */
@ObjectType(User.class)
public class UserIndex
        extends CoIndex<User> {

    public UserIndex() {
    }

}
