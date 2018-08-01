package net.bodz.lily.security;

import net.bodz.lily.security.UserId;

public class UserIdSamples {

    public static UserId build() {
        UserId a = new UserId();
        a.setLabel("userId-1");
        a.setDescription("A userId named userId-1.");
        return a;
    }

}
