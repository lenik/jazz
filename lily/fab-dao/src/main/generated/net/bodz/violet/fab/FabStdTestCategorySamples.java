package net.bodz.violet.fab;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.fab.dao.FabStdTestCategoryMapper;

public class FabStdTestCategorySamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public FabStdTestCategory parent;
    public User ownerUser;

    @Override
    public FabStdTestCategory build()
            throws Exception {
        FabStdTestCategory a = new FabStdTestCategory();
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        return a;
    }

    @Override
    public FabStdTestCategorySamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(FabStdTestCategoryMapper.class, "fabstdtestcat");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public FabStdTestCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
