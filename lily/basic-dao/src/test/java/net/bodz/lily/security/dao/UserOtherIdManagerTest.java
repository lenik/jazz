package net.bodz.lily.security.dao;

import net.bodz.lily.security.UserOtherId;
import net.bodz.lily.security.UserOtherIdSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class UserOtherIdManagerTest
        extends AbstractManagerTest<UserOtherId, UserOtherIdMapper, UserOtherIdManager> {

    @Override
    public UserOtherId buildSample()
            throws Exception {
        UserOtherIdSamples a = new UserOtherIdSamples();
        return a.buildWired(tables);
    }

}
