package net.bodz.lily.geo;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ZoneCategorySamples
        extends TestSampleBuilder {

    public ZoneCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    public ZoneCategory build()
            throws Exception {
        ZoneCategory a = new ZoneCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setId(1687097473);
        a.setDepth(387711147);
        a.setRefCount(173348754);
        return a;
    }

}
