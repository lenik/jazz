package net.bodz.violet.schema.store;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.store.dao.StoreCategoryMapper;

public class StoreCategorySamples
        extends TestSampleBuilder {

    public StoreCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public StoreCategory build()
            throws Exception {
        StoreCategory a = new StoreCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setName("Aeaee");
        return a;
    }

    @Override
    public StoreCategorySamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(StoreCategoryMapper.class, "storecat");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public StoreCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
