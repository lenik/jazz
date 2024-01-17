package net.bodz.lily.security.dao;

import net.bodz.lily.security.UserRun;
import net.bodz.lily.security.UserRunSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserRunMapperTest
        extends AbstractTableTest<UserRun, UserRunCriteriaBuilder, UserRunMapper> {

    @Override
    public UserRun buildSample()
            throws Exception {
        UserRunSamples a = new UserRunSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
