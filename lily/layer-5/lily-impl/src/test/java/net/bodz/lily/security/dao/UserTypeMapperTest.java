package net.bodz.lily.security.dao;

import net.bodz.lily.security.UserType;
import net.bodz.lily.security.UserTypeSamples;
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
