package net.bodz.lily.schema.vapp;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.vapp.dao.VAppCategoryMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class VAppCategorySamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public VAppCategory parent;

    @Override
    public VAppCategory build()
            throws Exception {
        VAppCategory a = new VAppCategory();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setName("Ywis fjdoi, Fwh.");
        return a;
    }

    @Override
    public VAppCategorySamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(VAppCategoryMapper.class, "vappcat");
        return this;
    }

    @Override
    public VAppCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
