package net.bodz.violet.schema.shop;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.shop.dao.SalesCategoryMapper;

public class SalesCategorySamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public SalesCategory parent;

    @Override
    public SalesCategory build()
            throws Exception {
        SalesCategory a = new SalesCategory();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        a.setName("Qecb.");
        return a;
    }

    @Override
    public SalesCategorySamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(SalesCategoryMapper.class, "salecat");
        return this;
    }

    @Override
    public SalesCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
