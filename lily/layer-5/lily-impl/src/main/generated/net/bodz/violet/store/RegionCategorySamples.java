package net.bodz.violet.store;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class RegionCategorySamples
        extends TestSampleBuilder {

    public RegionCategory parent;
    public User ownerUser;
    public Group ownerGroup;

    public RegionCategory build()
            throws Exception {
        RegionCategory a = new RegionCategory();
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

}
