package net.bodz.lily.model.base.security.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.base.security.UserSecret;
import net.bodz.lily.model.base.security.UserSecretSamples;
import net.bodz.lily.model.test.AbstractMapperTest;
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
