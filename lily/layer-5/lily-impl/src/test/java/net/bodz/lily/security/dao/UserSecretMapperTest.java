package net.bodz.lily.security.dao;

import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.UserSecretSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserSecretMapperTest
        extends AbstractTableTest<UserSecret, UserSecretMask, UserSecretMapper> {

    @Override
    public UserSecret buildSample()
            throws Exception {
        UserSecretSamples a = new UserSecretSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
