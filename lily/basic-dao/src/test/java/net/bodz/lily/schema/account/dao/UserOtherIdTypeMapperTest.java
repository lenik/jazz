package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.UserOtherIdType;
import net.bodz.lily.schema.account.UserOtherIdTypeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserOtherIdTypeMapperTest
        extends AbstractTableTest<UserOtherIdType, UserOtherIdTypeMapper> {

    @Override
    public UserOtherIdType buildSample()
            throws Exception {
        UserOtherIdTypeSamples a = new UserOtherIdTypeSamples();
        return a.buildWired(tables);
    }

}
