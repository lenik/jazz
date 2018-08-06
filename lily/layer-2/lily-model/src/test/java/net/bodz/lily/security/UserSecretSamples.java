package net.bodz.lily.security;

import net.bodz.lily.test.TestSamples;

public class UserSecretSamples
        extends TestSamples {

    public static UserSecret build(User user) {
        UserSecret a = new UserSecret();
        a.setLabel("userSecret-1");
        a.setDescription("A userSecret named userSecret-1.");
        a.setPassword("foo");
        a.setQuestion("Where is your home");
        a.setAnswer("newyork");
        a.setUser(user);
        return a;
    }

}
