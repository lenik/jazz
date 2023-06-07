package net.bodz.violet.shop;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ShopItemCategorySamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public ShopItemCategory parent;

    public ShopItemCategory build()
            throws Exception {
        ShopItemCategory a = new ShopItemCategory();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        return a;
    }

}
