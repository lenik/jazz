package net.bodz.lily.security.dao;

import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserMapperTest
        extends AbstractTableTest<User, UserMapper> {

    @Override
    public User buildSample()
            throws Exception {
        UserSamples a = new UserSamples();
        return a.buildWired(tables);
    }

}
