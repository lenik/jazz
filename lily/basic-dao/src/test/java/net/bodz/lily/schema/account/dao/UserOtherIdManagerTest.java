package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.UserOtherId;
import net.bodz.lily.schema.account.UserOtherIdSamples;
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
