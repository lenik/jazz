package net.bodz.lily.security.impl;

import org.junit.Ignore;

import net.bodz.lily.security.UserRun;
import net.bodz.lily.security.UserRunSamples;
import net.bodz.lily.test.AbstractTableTest;

@Ignore
public class UserRunMapperTest
        extends AbstractTableTest<UserRun, UserRunMask, UserRunMapper> {

    @Override
    public UserRun buildSample() {
        return UserRunSamples.build();
    }

}
