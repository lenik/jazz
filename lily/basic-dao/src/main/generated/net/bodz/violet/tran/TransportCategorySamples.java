package net.bodz.violet.tran;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.tran.dao.TransportCategoryMapper;

public class TransportCategorySamples
        extends TestSampleBuilder {

    public TransportCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public TransportCategory build()
            throws Exception {
        TransportCategory a = new TransportCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

    @Override
    public TransportCategorySamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(TransportCategoryMapper.class, "trancat");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public TransportCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
