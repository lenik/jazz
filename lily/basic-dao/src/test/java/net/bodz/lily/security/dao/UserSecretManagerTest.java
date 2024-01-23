package net.bodz.lily.security.dao;

import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.UserSecretSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class UserSecretManagerTest
        extends AbstractManagerTest<UserSecret, UserSecretMapper, UserSecretManager> {

    @Override
    public UserSecret buildSample()
            throws Exception {
        UserSecretSamples a = new UserSecretSamples();
        return a.buildWired(tables);
    }

}
