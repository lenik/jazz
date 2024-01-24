package net.bodz.violet.schema.shop;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.shop.dao.ShopTagMapper;

public class ShopTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public ShopTag parent;

    @Override
    public ShopTag build()
            throws Exception {
        ShopTag a = new ShopTag();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        return a;
    }

    @Override
    public ShopTagSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(ShopTagMapper.class, "shoptag");
        return this;
    }

    @Override
    public ShopTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
