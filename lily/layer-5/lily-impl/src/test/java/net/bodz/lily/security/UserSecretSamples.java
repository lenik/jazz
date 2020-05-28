package net.bodz.lily.security;

import net.bodz.lily.test.TestSamples;

public class UserSecretSamples
        extends TestSamples {

    public static UserSecret build(User user) {
        UserSecret a = new UserSecret();
        a.setUser(user);

        a.setLabel("userSecret-1");
        a.setDescription("A userSecret named userSecret-1.");
        a.setQuestion("Where is your home");
        a.setAnswer("newyork");

        a.setPassword("" + random.nextLong());
        a.setPublicKey("abcdefghijklmnopqrstuvwxyz");

        return a;
    }

}
