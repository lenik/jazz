package net.bodz.lily.security.dao;

import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSamples;
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
