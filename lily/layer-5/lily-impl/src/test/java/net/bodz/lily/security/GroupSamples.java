package net.bodz.lily.security;

import net.bodz.lily.test.TestSamples;

public class GroupSamples
        extends TestSamples {

    public static Group build(Group parent) {
        Group a = new Group();
        int rand = random.nextInt(10000);
        a.setName("group" + rand);
        a.setLabel("group-" + rand);
        a.setDescription("A group named group" + rand + ".");
        a.setParent(parent);
        return a;
    }

}
