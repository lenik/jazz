package net.bodz.lily.schema.account.dao;

import net.bodz.lily.schema.account.UserRun;
import net.bodz.lily.schema.account.UserRunSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class UserRunManagerTest
        extends AbstractManagerTest<UserRun, UserRunMapper, UserRunManager> {

    @Override
    public UserRun buildSample()
            throws Exception {
        UserRunSamples a = new UserRunSamples();
        return a.buildWired(tables);
    }

}
