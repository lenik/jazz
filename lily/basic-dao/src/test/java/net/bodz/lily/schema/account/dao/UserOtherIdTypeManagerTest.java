package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.UserOtherIdType;
import net.bodz.lily.schema.account.UserOtherIdTypeSamples;
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
