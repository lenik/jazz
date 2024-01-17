package net.bodz.violet.plan;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PlanParameterSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    @Override
    public PlanParameter build()
            throws Exception {
        PlanParameter a = new PlanParameter();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setDummy(1810228722);
        return a;
    }

    @Override
    public PlanParameterSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public PlanParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
