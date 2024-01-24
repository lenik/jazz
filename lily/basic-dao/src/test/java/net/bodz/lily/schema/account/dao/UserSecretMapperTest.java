package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.UserSecret;
import net.bodz.lily.schema.account.UserSecretSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserSecretMapperTest
        extends AbstractTableTest<UserSecret, UserSecretMapper> {

    @Override
    public UserSecret buildSample()
            throws Exception {
        UserSecretSamples a = new UserSecretSamples();
        return a.buildWired(tables);
    }

}
