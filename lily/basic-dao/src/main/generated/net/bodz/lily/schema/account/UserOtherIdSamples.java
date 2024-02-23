package net.bodz.lily.schema.account;

import java.time.ZonedDateTime;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.account.dao.UserOtherIdTypeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class UserOtherIdSamples
        extends TestSampleBuilder {

    public UserOtherIdType type;
    public User user;

    @Override
    public UserOtherId build()
            throws Exception {
        UserOtherId a = new UserOtherId();
        a.setType(type);
        a.setUser(user);
        a.setId(954758213);
        a.setBeginTime(ZonedDateTime.parse("2024-01-23T22:12:05.853 +0000", DateTimes.ISO8601));
        a.setEndTime(ZonedDateTime.parse("2024-01-21T23:11:12.790 -1000", DateTimes.ISO8601));
        a.setYear(507267842);
        a.setOtherId("ciuewu'rklar eeb qyaf! up! uzda, irp! daslq; kio paee! oue@evgr, rbeen uee ruo Ee");
        return a;
    }

    @Override
    public UserOtherIdSamples wireAny(IRandomPicker picker) {
        this.type = picker.pickAny(UserOtherIdTypeMapper.class, "useroidtype");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public UserOtherId buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
