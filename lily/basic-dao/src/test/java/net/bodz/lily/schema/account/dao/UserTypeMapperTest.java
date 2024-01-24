package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.UserType;
import net.bodz.lily.schema.account.UserTypeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserTypeMapperTest
        extends AbstractTableTest<UserType, UserTypeMapper> {

    @Override
    public UserType buildSample()
            throws Exception {
        UserTypeSamples a = new UserTypeSamples();
        return a.buildWired(tables);
    }

}
