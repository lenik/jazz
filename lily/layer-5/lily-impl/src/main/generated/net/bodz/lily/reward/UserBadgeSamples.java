package net.bodz.lily.reward;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class UserBadgeSamples
        extends TestSampleBuilder {

    public Badge badge;
    public User user;

    public UserBadge build()
            throws Exception {
        UserBadge a = new UserBadge();
        a.setBadge(badge);
        a.setUser(user);
        a.setId(178419693);
        return a;
    }

}
