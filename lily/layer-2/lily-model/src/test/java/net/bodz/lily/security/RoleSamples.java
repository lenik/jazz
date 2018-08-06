package net.bodz.lily.security;

import net.bodz.lily.test.TestSamples;

public class RoleSamples
        extends TestSamples {

    public static Role build() {
        Role a = new Role();
        int rand = random.nextInt(10000);
        a.setName("role" + rand);
        a.setLabel("role-" + rand);
        a.setDescription("A role named role" + rand + ".");
        a.setRank(random.nextInt(100));
        return a;
    }

}
