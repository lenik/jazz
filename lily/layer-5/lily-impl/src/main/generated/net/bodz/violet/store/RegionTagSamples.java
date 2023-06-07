package net.bodz.violet.store;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class RegionTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public RegionTag parent;
    public Group ownerGroup;

    public RegionTag build()
            throws Exception {
        RegionTag a = new RegionTag();
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

}
