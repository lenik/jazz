package net.bodz.lily.security.impl;

import org.junit.Ignore;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.UserRun;
import net.bodz.lily.security.UserRunSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

@Ignore
public class UserRunMapperTest
        extends AbstractMapperTest<UserRun, UserRunMask, UserRunMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public UserRun buildSample() {
        return UserRunSamples.build();
    }

}
