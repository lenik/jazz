package net.bodz.lily.security.impl;

import net.bodz.lily.security.User;
import net.bodz.lily.security.UserOtherId;
import net.bodz.lily.security.UserOtherIdSamples;
import net.bodz.lily.security.UserOtherIdType;
import net.bodz.lily.test.AbstractMapperTest;

public class UserOtherIdMapperTest
        extends AbstractMapperTest<UserOtherId, UserOtherIdMask, UserOtherIdMapper> {

    @Override
    public UserOtherId buildSample() {
        User user = tables.pickAny(UserMapper.class, "user");
        UserOtherIdType type = tables.pickAny(UserOtherIdTypeMapper.class, "useroidtype");
        return UserOtherIdSamples.build(user, type);
    }

}
