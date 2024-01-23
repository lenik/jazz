package net.bodz.violet.store;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.store.dao.RegionCategoryMapper;

public class RegionCategorySamples
        extends TestSampleBuilder {

    public RegionCategory parent;
    public User ownerUser;
    public Group ownerGroup;

    @Override
    public RegionCategory build()
            throws Exception {
        RegionCategory a = new RegionCategory();
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

    @Override
    public RegionCategorySamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(RegionCategoryMapper.class, "regioncat");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public RegionCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
