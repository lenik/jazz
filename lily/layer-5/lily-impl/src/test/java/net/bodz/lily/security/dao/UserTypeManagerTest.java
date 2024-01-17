package net.bodz.lily.security.dao;

import net.bodz.lily.security.UserType;
import net.bodz.lily.security.UserTypeSamples;
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
