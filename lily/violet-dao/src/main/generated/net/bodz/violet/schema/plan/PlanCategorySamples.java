package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.plan.dao.PlanCategoryMapper;

public class PlanCategorySamples
        extends TestSampleBuilder {

    public PlanCategory parent;
    public User ownerUser;
    public Group ownerGroup;

    @Override
    public PlanCategory build()
            throws Exception {
        PlanCategory a = new PlanCategory();
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

    @Override
    public PlanCategorySamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(PlanCategoryMapper.class, "plancat");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public PlanCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
