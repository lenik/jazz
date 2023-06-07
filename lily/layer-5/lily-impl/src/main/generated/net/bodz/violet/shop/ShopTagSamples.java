package net.bodz.violet.shop;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ShopTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public ShopTag parent;

    public ShopTag build()
            throws Exception {
        ShopTag a = new ShopTag();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        return a;
    }

}
