package net.bodz.lily.security.dao;

import net.bodz.lily.security.UserOtherIdType;
import net.bodz.lily.security.UserOtherIdTypeSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class UserOtherIdTypeManagerTest
        extends AbstractManagerTest<UserOtherIdType, UserOtherIdTypeMapper, UserOtherIdTypeManager> {

    @Override
    public UserOtherIdType buildSample()
            throws Exception {
        UserOtherIdTypeSamples a = new UserOtherIdTypeSamples();
        return a.buildWired(tables);
    }

}
