package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.UserSamples;
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
