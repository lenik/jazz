package net.bodz.lily.security.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.UserSecretSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class UserSecretMapperTest
        extends AbstractMapperTest<UserSecret, UserSecretMask, UserSecretMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public UserSecret buildSample() {
        return UserSecretSamples.build();
    }

}
