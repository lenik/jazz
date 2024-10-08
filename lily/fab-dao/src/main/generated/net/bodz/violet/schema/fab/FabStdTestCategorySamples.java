package net.bodz.violet.schema.fab;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.fab.dao.FabStdTestCategoryMapper;

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
        a.setName("u-k. reb.");
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
