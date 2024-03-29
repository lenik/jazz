package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.plan.dao.PlanTagMapper;

public class PlanTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public PlanTag parent;

    @Override
    public PlanTag build()
            throws Exception {
        PlanTag a = new PlanTag();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        return a;
    }

    @Override
    public PlanTagSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(PlanTagMapper.class, "plantag");
        return this;
    }

    @Override
    public PlanTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
