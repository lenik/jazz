package net.bodz.lily.security.impl;

import org.junit.Ignore;

import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.UserSecretSamples;
import net.bodz.lily.test.AbstractTableTest;

@Ignore
public class UserSecretMapperTest
        extends AbstractTableTest<UserSecret, UserSecretMask, UserSecretMapper> {

    @Override
    public UserSecret buildSample() {
        User user = tables.pickAny(UserMapper.class, "user");
        return UserSecretSamples.build(user);
    }

}
