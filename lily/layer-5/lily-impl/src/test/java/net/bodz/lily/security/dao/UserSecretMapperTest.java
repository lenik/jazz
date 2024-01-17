package net.bodz.lily.security.dao;

import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.UserSecretSamples;
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
