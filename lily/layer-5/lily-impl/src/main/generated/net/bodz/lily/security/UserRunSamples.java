package net.bodz.lily.security;

import java.sql.Timestamp;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.test.TestSampleBuilder;

public class UserRunSamples
        extends TestSampleBuilder {

    public User user;

    public UserRun build()
            throws Exception {
        UserRun a = new UserRun();
        a.setUser(user);
        a.setScore(961999915);
        a.setLastLoginTime(new Timestamp(Dates.ISO8601Z.parse("2023-01-01T10:30:49.128+0800").getTime()));
        return a;
    }

}
