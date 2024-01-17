package net.bodz.lily.vapp;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.lily.vapp.dao.VAppCatMapper;

public class VAppCatSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public VAppCat parent;

    @Override
    public VAppCat build()
            throws Exception {
        VAppCat a = new VAppCat();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setId(1191060333);
        a.setDepth(1976022062);
        a.setRefCount(1067875411);
        return a;
    }

    @Override
    public VAppCatSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(VAppCatMapper.class, "vappcat");
        return this;
    }

    @Override
    public VAppCat buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
