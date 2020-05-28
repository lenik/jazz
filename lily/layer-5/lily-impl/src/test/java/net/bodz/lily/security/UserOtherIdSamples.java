package net.bodz.lily.security;

import net.bodz.lily.test.TestSamples;

public class UserOtherIdSamples
        extends TestSamples {

    public static UserOtherId build(User user, UserOtherIdType type) {
        UserOtherId a = new UserOtherId();
        a.setUser(user);
        a.setType(type);
        a.setOtherId("oid-" + random.nextLong());
        return a;
    }

}
