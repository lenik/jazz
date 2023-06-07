package net.bodz.violet.plan;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PlanDoParameterSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    public PlanDoParameter build()
            throws Exception {
        PlanDoParameter a = new PlanDoParameter();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setDummy(1707472293);
        return a;
    }

}
