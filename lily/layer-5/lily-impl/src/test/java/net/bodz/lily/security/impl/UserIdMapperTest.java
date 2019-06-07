package net.bodz.lily.security.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserId;
import net.bodz.lily.security.UserIdSamples;
import net.bodz.lily.security.UserIdType;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class UserIdMapperTest
        extends AbstractMapperTest<UserId, UserIdMask, UserIdMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public UserId buildSample() {
        User user = tables.pickAny(UserMapper.class, "user");
        UserIdType type = tables.pickAny(UserIdTypeMapper.class, "useridtype");
        return UserIdSamples.build(user, type);
    }

}
