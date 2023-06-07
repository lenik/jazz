package net.bodz.violet.plan;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PlanParameterSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    public PlanParameter build()
            throws Exception {
        PlanParameter a = new PlanParameter();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setDummy(1810228722);
        return a;
    }

}
