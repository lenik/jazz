package net.bodz.lily.security;

import net.bodz.lily.test.TestSampleBuilder;

public class GroupSamples
        extends TestSampleBuilder {

    public Group parent;
    public GroupType type;

    public Group build()
            throws Exception {
        Group a = new Group();
        a.setParent(parent);
        a.setType(type);
        return a;
    }

}
