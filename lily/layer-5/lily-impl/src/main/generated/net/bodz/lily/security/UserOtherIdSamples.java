package net.bodz.lily.security;

import org.joda.time.DateTime;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.security.dao.UserOtherIdTypeMapper;
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
        a.setBeginTime(new DateTime(Dates.ISO8601Z.parse("2024-01-23T22:12:05.853+0800").getTime()));
        a.setEndTime(new DateTime(Dates.ISO8601Z.parse("2023-12-18T17:32:56.454+0800").getTime()));
        a.setYear(52230391);
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
