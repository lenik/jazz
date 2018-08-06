package net.bodz.lily.security;

import net.bodz.lily.test.TestSamples;

public class PolicySamples
        extends TestSamples {

    public static Policy build(Group group, User user) {
        Policy a = new Policy();
        a.setLabel("policy-1");
        a.setDescription("A policy named policy-1.");
        a.setGroup(group);
        a.setUser(user);
        a.setControlClass(PolicySamples.class.getName());
        return a;
    }

}
