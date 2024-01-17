package net.bodz.lily.security;

import java.sql.Timestamp;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class UserRunSamples
        extends TestSampleBuilder {

    public User user;

    @Override
    public UserRun build()
            throws Exception {
        UserRun a = new UserRun();
        a.setUser(user);
        a.setScore(961999915);
        a.setLastLoginTime(new Timestamp(Dates.ISO8601Z.parse("2024-01-01T10:30:49.128+0800").getTime()));
        return a;
    }

    @Override
    public UserRunSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public UserRun buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
