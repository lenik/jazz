package net.bodz.violet.plan;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.plan.dao.PlanDoTagMapper;

public class PlanDoTagSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public PlanDoTag parent;
    public User ownerUser;

    @Override
    public PlanDoTag build()
            throws Exception {
        PlanDoTag a = new PlanDoTag();
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        return a;
    }

    @Override
    public PlanDoTagSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(PlanDoTagMapper.class, "plandotag");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public PlanDoTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
