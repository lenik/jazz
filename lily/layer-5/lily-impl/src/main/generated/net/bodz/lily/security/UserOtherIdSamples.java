package net.bodz.lily.security;

import org.joda.time.DateTime;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.test.TestSampleBuilder;

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
        a.setBeginTime(new DateTime(Dates.ISO8601Z.parse("2023-01-23T22:12:05.853+0800").getTime()));
        a.setEndTime(new DateTime(Dates.ISO8601Z.parse("2022-12-18T17:32:56.454+0800").getTime()));
        a.setYear(52230391);
        a.setOtherId("ciuewu'rklar eeb qyaf! up! uzda, irp! daslq; kio paee! oue@evgr, rbeen uee ruo Ee");
        return a;
    }

}
