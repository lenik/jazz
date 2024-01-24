package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.UserSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class UserManagerTest
        extends AbstractManagerTest<User, UserMapper, UserManager> {

    @Override
    public User buildSample()
            throws Exception {
        UserSamples a = new UserSamples();
        return a.buildWired(tables);
    }

}
