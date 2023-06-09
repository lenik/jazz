package net.bodz.lily.security.dao;

import net.bodz.lily.security.UserOtherId;
import net.bodz.lily.security.UserOtherIdSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserOtherIdMapperTest
        extends AbstractTableTest<UserOtherId, UserOtherIdMask, UserOtherIdMapper> {

    @Override
    public UserOtherId buildSample()
            throws Exception {
        UserOtherIdSamples a = new UserOtherIdSamples();
        a.type = tables.pickAny(UserOtherIdTypeMapper.class, "useroidtype");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
