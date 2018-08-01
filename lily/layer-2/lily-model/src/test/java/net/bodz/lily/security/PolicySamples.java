package net.bodz.lily.security;

public class PolicySamples {

    public static Policy build() {
        Policy a = new Policy();
        a.setLabel("policy-1");
        a.setDescription("A policy named policy-1.");
        return a;
    }

}
