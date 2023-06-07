package net.bodz.violet.store;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class StoreCategorySamples
        extends TestSampleBuilder {

    public StoreCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    public StoreCategory build()
            throws Exception {
        StoreCategory a = new StoreCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

}
