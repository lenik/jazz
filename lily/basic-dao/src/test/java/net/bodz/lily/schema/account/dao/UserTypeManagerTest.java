package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.UserType;
import net.bodz.lily.schema.account.UserTypeSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class UserTypeManagerTest
        extends AbstractManagerTest<UserType, UserTypeMapper, UserTypeManager> {

    @Override
    public UserType buildSample()
            throws Exception {
        UserTypeSamples a = new UserTypeSamples();
        return a.buildWired(tables);
    }

}
