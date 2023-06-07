package net.bodz.violet.shop;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ShopCategorySamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public ShopCategory parent;

    public ShopCategory build()
            throws Exception {
        ShopCategory a = new ShopCategory();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        return a;
    }

}
