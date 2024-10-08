package net.bodz.violet.schema.shop;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.shop.dao.ShopCategoryMapper;

public class ShopCategorySamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public ShopCategory parent;

    @Override
    public ShopCategory build()
            throws Exception {
        ShopCategory a = new ShopCategory();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setName("ouw");
        return a;
    }

    @Override
    public ShopCategorySamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(ShopCategoryMapper.class, "shopcat");
        return this;
    }

    @Override
    public ShopCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
