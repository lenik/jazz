package net.bodz.violet.schema.shop;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.shop.dao.ShopItemCategoryMapper;

public class ShopItemCategorySamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public ShopItemCategory parent;

    @Override
    public ShopItemCategory build()
            throws Exception {
        ShopItemCategory a = new ShopItemCategory();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setName("Dupoed aauw; eigp, cuf. nlo.");
        return a;
    }

    @Override
    public ShopItemCategorySamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(ShopItemCategoryMapper.class, "shopitemcat");
        return this;
    }

    @Override
    public ShopItemCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
