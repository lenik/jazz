package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PlanDoParameterSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    @Override
    public PlanDoParameter build()
            throws Exception {
        PlanDoParameter a = new PlanDoParameter();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setDummy(1707472293);
        return a;
    }

    @Override
    public PlanDoParameterSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public PlanDoParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
