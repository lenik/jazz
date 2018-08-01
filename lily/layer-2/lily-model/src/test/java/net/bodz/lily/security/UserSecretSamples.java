package net.bodz.lily.security;

import net.bodz.lily.security.UserSecret;

public class UserSecretSamples {

    public static UserSecret build() {
        UserSecret a = new UserSecret();
        a.setLabel("userSecret-1");
        a.setDescription("A userSecret named userSecret-1.");
        return a;
    }

}
