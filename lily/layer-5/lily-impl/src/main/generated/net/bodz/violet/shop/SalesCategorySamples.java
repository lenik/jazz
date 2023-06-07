package net.bodz.violet.shop;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class SalesCategorySamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public SalesCategory parent;

    public SalesCategory build()
            throws Exception {
        SalesCategory a = new SalesCategory();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        return a;
    }

}
