package net.bodz.violet.fab;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.fab.dao.FabStdTestCategoryMapper;
import net.bodz.violet.fab.dao.FabStdTestMapper;

public class FabStdTestSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public FabStdTestCategory category;
    public Group ownerGroup;
    public FabStdTest parent;

    @Override
    public FabStdTest build()
            throws Exception {
        FabStdTest a = new FabStdTest();
        a.setOwnerUser(ownerUser);
        a.setCategory(category);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        a.setId(1636779479);
        a.setCode("Otku apaj o moe sze.");
        a.setDepth(1095746246);
        a.setRefCount(802854516);
        return a;
    }

    @Override
    public FabStdTestSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.category = picker.pickAny(FabStdTestCategoryMapper.class, "fabstdtestcat");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(FabStdTestMapper.class, "fabstdtest");
        return this;
    }

    @Override
    public FabStdTest buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
