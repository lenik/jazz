package net.bodz.lily.security;

import net.bodz.lily.test.TestSamples;

public class UserSamples
        extends TestSamples {

    public static User build(Group primaryGroup) {
        User a = new User();
        int rand = random.nextInt(10000);
        a.setName("user" + rand);
        a.setLabel("user-" + rand);
        a.setDescription("A user named user" + rand + ".");
        a.setPrimaryGroup(primaryGroup);

        UserSecret secret = UserSecretSamples.build(a);
        a.setSecret(secret);
        return a;
    }

}
