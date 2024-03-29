package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.UserOtherId;
import net.bodz.lily.schema.account.UserOtherIdSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserOtherIdMapperTest
        extends AbstractTableTest<UserOtherId, UserOtherIdMapper> {

    @Override
    public UserOtherId buildSample()
            throws Exception {
        UserOtherIdSamples a = new UserOtherIdSamples();
        return a.buildWired(tables);
    }

}
