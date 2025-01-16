package net.bodz.lily.schema.account;

import java.time.OffsetDateTime;

import net.bodz.bas.c.java.time.DateTimes;
import net.bodz.lily.schema.account.dao.UserMapper;
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
        a.setLastLoginTime(OffsetDateTime.parse("2025-01-01T12:41:49.128+02:11", DateTimes.ISO_OFFSET_DATE_TIME));
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
