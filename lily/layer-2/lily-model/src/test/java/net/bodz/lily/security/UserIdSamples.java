package net.bodz.lily.security;

import net.bodz.lily.test.TestSamples;

public class UserIdSamples
        extends TestSamples {

    public static UserId build(User user, UserIdType type) {
        UserId a = new UserId();
        a.setUser(user);
        a.setType(type);
        a.setOid("oid-" + random.nextLong());
        return a;
    }

}
