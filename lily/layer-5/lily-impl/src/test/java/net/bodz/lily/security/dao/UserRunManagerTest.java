package net.bodz.lily.security.dao;

import net.bodz.lily.security.UserRun;
import net.bodz.lily.security.UserRunSamples;
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
