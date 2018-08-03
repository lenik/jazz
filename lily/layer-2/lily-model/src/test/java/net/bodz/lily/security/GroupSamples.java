package net.bodz.lily.security;

import java.util.Random;

public class GroupSamples {

    static Random random = new Random();

    public static Group build(User admin) {
        Group a = new Group();
        a.setCodeName("group" + random.nextInt(1000));
        a.setLabel("group-1");
        a.setDescription("A group named group-1.");
        a.setAdmin(admin);
        return a;
    }

}
