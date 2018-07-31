package net.bodz.lily.model.base.security.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.base.security.UserId;
import net.bodz.lily.model.base.security.UserIdSamples;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class UserIdMapperTest
        extends AbstractMapperTest<UserId, UserIdMask, UserIdMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public UserId buildSample() {
        return UserIdSamples.build();
    }

}
